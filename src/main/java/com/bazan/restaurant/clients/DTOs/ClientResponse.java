package com.bazan.restaurant.clients.DTOs;

import com.bazan.restaurant.clients.Client;
import com.bazan.restaurant.shared.DTOs.Response;

public class ClientResponse extends Response<Client> {

    public ClientResponse(boolean success, String message, Client data) {
        super(success, message, data);
    }

    public static ClientResponse Success(Client client, String message) {
        return new ClientResponse(true, message, client);
    }

    public static ClientResponse Failure(String message) {
        return new ClientResponse(false, message, null);
    }
}
