package com.example.cqsexample;

import java.time.LocalDateTime;

public class LPN {
    private final Long id;
    private final String productSerialNumber;
    private int stockQuantity;
    private final LocalDateTime receivedAt;
    private final LocalDateTime expiredAt;

    public LPN(final long id, final String productSerialNumber, final int stockQuantity, final LocalDateTime receivedAt, final LocalDateTime expiredAt) {
        this.id = id;
        this.productSerialNumber = productSerialNumber;
        this.stockQuantity = stockQuantity;
        this.receivedAt = receivedAt;
        this.expiredAt = expiredAt;
    }

    public Long getId() {
        return id;
    }

    public String getProductSerialNumber() {
        return productSerialNumber;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void decreaseQuantity(final int allocateQuantity) {
        stockQuantity -= allocateQuantity;
    }

    @Override
    public String toString() {
        return "LPN{" +
                "id=" + id +
                ", productSerialNumber='" + productSerialNumber + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", receivedAt=" + receivedAt +
                ", expiredAt=" + expiredAt +
                '}';
    }
}
