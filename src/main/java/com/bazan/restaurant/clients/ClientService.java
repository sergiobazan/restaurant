package com.bazan.restaurant.clients;

import com.bazan.restaurant.clients.DTOs.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client create(ClientRequest clientRequest) {
        var client = Client.create(
                clientRequest.getName(),
                clientRequest.getEmail(),
                clientRequest.getBirthDay());
        return clientRepository.save(client);
    }
}
