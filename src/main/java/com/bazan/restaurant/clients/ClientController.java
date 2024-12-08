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

    private final IClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(
            @RequestBody ClientRequest request
    ) {
        try {
            var client = Client.create(
                    request.getName(),
                    request.getEmail(),
                    request.getBirthDay());
            var result = clientRepository.save(client);
            var response = ClientResponse.Success(result, "Client created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            var response = ClientResponse.Failure(ex.getLocalizedMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
