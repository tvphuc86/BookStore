package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.config.Config;
import com.bookstore.dto.PaymentInforDTO;
import com.bookstore.dto.PaymentReponseDTO;
import com.bookstore.service.VNPayService;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@CrossOrigin
@RestController
@RequestMapping("/payments")
public class PaymentController {




    @GetMapping("/vnpay")
    protected ResponseEntity<?> doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 String vnp_Version = "2.1.0";
         String vnp_Command = "pay";
         String orderType = "other";
         long amount = Integer.parseInt(req.getParameter("vnp_Amount"))*100;
         String bankCode = req.getParameter("bankCode");
         
         String vnp_TxnRef = Config.getRandomNumber(8);
         String vnp_IpAddr = Config.getIpAddress(req);

         String vnp_TmnCode = Config.vnp_TmnCode;
         
         Map<String, String> vnp_Params = new HashMap<>();
         vnp_Params.put("vnp_Version", vnp_Version);
         vnp_Params.put("vnp_Command", vnp_Command);
         vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
         vnp_Params.put("vnp_Amount", String.valueOf(amount));
         vnp_Params.put("vnp_CurrCode", "VND");
         
         if (bankCode != null && !bankCode.isEmpty()) {
             vnp_Params.put("vnp_BankCode", bankCode);
         }
         vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
         vnp_Params.put("vnp_OrderInfo", "Thanh toan don han");
         vnp_Params.put("vnp_OrderType", orderType);

         String locate = req.getParameter("language");
         if (locate != null && !locate.isEmpty()) {
             vnp_Params.put("vnp_Locale", locate);
         } else {
             vnp_Params.put("vnp_Locale", "vn");
         }
         vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
         vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

         Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
         SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
         String vnp_CreateDate = formatter.format(cld.getTime());
         vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
         
         cld.add(Calendar.MINUTE, 15);
         String vnp_ExpireDate = formatter.format(cld.getTime());
         vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
         
         List fieldNames = new ArrayList(vnp_Params.keySet());
         Collections.sort(fieldNames);
         StringBuilder hashData = new StringBuilder();
         StringBuilder query = new StringBuilder();
         Iterator itr = fieldNames.iterator();
         while (itr.hasNext()) {
             String fieldName = (String) itr.next();
             String fieldValue = (String) vnp_Params.get(fieldName);
             if ((fieldValue != null) && (fieldValue.length() > 0)) {
                 //Build hash data
                 hashData.append(fieldName);
                 hashData.append('=');
                 hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                 //Build query
                 query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                 query.append('=');
                 query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                 if (itr.hasNext()) {
                     query.append('&');
                     hashData.append('&');
                 }
             }
         }
         String queryUrl = query.toString();
         String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
         queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
         String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
         PaymentReponseDTO paymentReponseDTO= new PaymentReponseDTO();
         paymentReponseDTO.setStatus("Ok");
         paymentReponseDTO.setStatus("seccessfully");
         paymentReponseDTO.setUrl(paymentUrl);
//         com.google.gson.JsonObject job = new JsonObject();
//         job.addProperty("code", "00");
//         job.addProperty("message", "success");
//         job.addProperty("data", paymentUrl);
//         Gson gson = new Gson();
//         resp.getWriter().write(gson.toJson(job));
         return ResponseEntity.status(HttpStatus.OK).body(paymentReponseDTO);
    }
    @GetMapping("/payment-info")
    public ResponseEntity<?> paymentInfo(@RequestParam(value = "vnp_Amount") String amount,
    		@RequestParam(value = "vnp_OrderInfo") String orderInfo,
    		@RequestParam(value = "vnp_TransactionStatus") String transactionStatus,
    		@RequestParam(value = "vnp_PayDate") String payDate,
    		@RequestParam(value = "vnp_TransactionNo") String transactionNo,
    		@RequestParam(value = "vnp_BankTranNo") String bankTranNo
    		){
    	System.out.println(transactionStatus);
    	PaymentInforDTO dto = new PaymentInforDTO();
    
    	if (transactionStatus.equals("00")) {
    		
    		dto.setAmount(amount);
        	dto.setBankTranNo(bankTranNo);
        	dto.setMessage("successfully");
        	dto.setStatus("Ok");
        	dto.setOrderInfo(orderInfo);
        	dto.setTransactionNo(transactionNo);

    		return ResponseEntity.status(HttpStatus.OK).body(dto);
    	}
    	else {
   
    		dto.setMessage("Failed");
        	dto.setStatus("Failed");

    		return ResponseEntity.status(HttpStatus.OK).body(dto);
    	}
    }
}