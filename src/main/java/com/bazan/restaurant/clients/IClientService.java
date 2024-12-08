package com.bazan.restaurant.clients;

import com.bazan.restaurant.clients.DTOs.ClientRequest;

import java.util.List;

public interface IClientService {
    List<Client> getAll();
    Client create(ClientRequest clientRequest);
}
