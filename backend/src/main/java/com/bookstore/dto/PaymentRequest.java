package com.bookstore.dto;

public class PaymentRequest {
    private String amount;
    private String createDate;
    // ...Thêm các thuộc tính thanh toán khác, getter và setter

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreateDate() {

        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}