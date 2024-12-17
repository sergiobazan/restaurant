package com.bazan.restaurant.orders;

public enum PaymentStatus {
    PENDING(0),
    PAID(1);

    private final int value;

    PaymentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static PaymentStatus fromValue(int value) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.getValue() == value) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("Invalid value for OrderStatus: " + value);
    }
}
