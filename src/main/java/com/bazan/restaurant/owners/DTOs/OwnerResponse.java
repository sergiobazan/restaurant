package com.bazan.restaurant.owners.DTOs;

import com.bazan.restaurant.owners.Owner;
import com.bazan.restaurant.shared.DTOs.Response;

public class OwnerResponse extends Response<Owner> {

    private OwnerResponse(boolean success, String message, Owner data) {
        super(success, message, data);
    }

    public static OwnerResponse Success(String message, Owner data) {
        return new OwnerResponse(true, message, data);
    }

    public static OwnerResponse Failure(String message) {
        return new OwnerResponse(false, message, null);
    }
}
