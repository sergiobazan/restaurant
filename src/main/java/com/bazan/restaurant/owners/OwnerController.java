package com.bazan.restaurant.owners;

import com.bazan.restaurant.owners.DTOs.OwnerRequest;
import com.bazan.restaurant.owners.DTOs.OwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("owners")
public class OwnerController {

    private final IOwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(
            @RequestBody OwnerRequest ownerRequest
    ) {
        try {
            var owner = ownerService.create(ownerRequest);
            var result = OwnerResponse.Success("Owner created successfully", owner);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            var result = OwnerResponse.Failure("Can not create owner");
            return ResponseEntity.badRequest().body(result);
        }

    }
}
