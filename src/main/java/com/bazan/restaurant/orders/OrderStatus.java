package com.bazan.restaurant.orders;

public enum OrderStatus {
    PENDING(0),
    CONFIRMED(1),
    DELIVERED(2);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // Method to parse an integer to the enum
    public static OrderStatus fromValue(int value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value for OrderStatus: " + value);
    }
}
