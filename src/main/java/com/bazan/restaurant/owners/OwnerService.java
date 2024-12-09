package com.bazan.restaurant.owners;

import com.bazan.restaurant.owners.DTOs.OwnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OwnerService implements IOwnerService {

    private final IOwnerRepository ownerRepository;

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner create(OwnerRequest ownerRequest) {
        var owner = Owner.create(
                ownerRequest.name(),
                ownerRequest.email(),
                ownerRequest.birthDate()
        );
        return ownerRepository.save(owner);
    }
}
