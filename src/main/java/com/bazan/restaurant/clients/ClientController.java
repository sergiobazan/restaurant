package com.bazan.restaurant.clients;

import com.bazan.restaurant.clients.DTOs.ClientRequest;
import com.bazan.restaurant.clients.DTOs.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final IClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(
            @RequestBody ClientRequest request
    ) {
        try {
            var result = clientService.create(request);
            var response = ClientResponse.Success(result, "Client created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            var response = ClientResponse.Failure(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
