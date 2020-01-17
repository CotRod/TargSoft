package com.github.cotrod.targsoft.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private LocalDateTime date;
    private BigDecimal amount;
    private String merchant;
    private TypeOfTransaction type;

    public Transaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public TypeOfTransaction getType() {
        return type;
    }

    public void setType(TypeOfTransaction type) {
        this.type = type;
    }

}
