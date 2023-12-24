package com.bookstore.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bookstore.dto.CallbackRequest;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Service
public class VNPayService {

    private static final String SECURE_HASH_TYPE = "SHA256";
    private static final String ENCODING_TYPE = "UTF-8";

    @Value("${vnpay.secret-key}")
    private String secretKey;
    public String generateSecureHash(Map<String, String> data) throws NoSuchAlgorithmException {
        Map<String, String> sortedData = new TreeMap<>(data);
        StringBuilder stringBuilder = new StringBuilder();

        sortedData.forEach((key, value) -> {
            if (!"vnp_SecureHash".equals(key)) {
                stringBuilder.append(key).append("=").append(value).append("&");
            }
        });

        String input = stringBuilder.toString();
        input += "vnp_SecureSecret=" + secretKey;

        MessageDigest digest = MessageDigest.getInstance(SECURE_HASH_TYPE);
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hashBuilder = new StringBuilder();
        for (byte b : hashBytes) {
            hashBuilder.append(String.format("%02x", b));
        }

        return hashBuilder.toString();
    }

    public boolean isValidCallback(CallbackRequest callbackRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        String requestDataString = convertMapToString(callbackRequest.getData());
        String receivedSignature = callbackRequest.getSignature();
        String expectedSignature = generateDigitalSignature(requestDataString);
        return receivedSignature.equals(expectedSignature);
    }


    public String generateDigitalSignature(String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
        sha256Hmac.init(secretKeySpec);
        byte[] signatureBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signatureBytes);
    }
	public String convertMapToString(Map<String, String> data) {
        StringBuilder stringBuilder = new StringBuilder();

        data.forEach((key, value) -> {
            stringBuilder.append(key).append("=").append(value).append("&");
        });

        return stringBuilder.toString();
    }
}