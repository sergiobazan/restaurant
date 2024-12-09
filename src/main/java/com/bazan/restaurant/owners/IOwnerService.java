package com.bazan.restaurant.owners;

import com.bazan.restaurant.owners.DTOs.OwnerRequest;

import java.util.List;

public interface IOwnerService {
    List<Owner> getAll();
    Owner create(OwnerRequest ownerRequest);
}
