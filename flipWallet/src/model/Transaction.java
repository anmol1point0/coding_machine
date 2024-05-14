package model;

import enums.TRANSACTION_TYPE;

import java.math.BigDecimal;

public class Transaction {

    private String senderName;
    private String recipientName;
    private Long transactionTimeStamp;
    private BigDecimal amount;
    private TRANSACTION_TYPE transactionType;

    public String getSenderName() {
        return senderName;
    }

    public TRANSACTION_TYPE getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TRANSACTION_TYPE transactionType) {
        this.transactionType = transactionType;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public Long getTransactionTimeStamp() {
        return transactionTimeStamp;
    }

    public void setTransactionTimeStamp(Long transactionTimeStamp) {
        this.transactionTimeStamp = transactionTimeStamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
