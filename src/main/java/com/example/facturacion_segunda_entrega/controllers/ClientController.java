package com.example.facturacion_segunda_entrega.controllers;

import com.example.facturacion_segunda_entrega.DTO.ClientDTO;
import com.example.facturacion_segunda_entrega.entities.Client;
import com.example.facturacion_segunda_entrega.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Map<String, Object>> getAllClients() {
        List<Client> clients = clientService.getAllClients();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Clients:");
        response.put("data", clients);

        return ResponseEntity
                .ok()
                .body(response);
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> saveClient(@RequestBody ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setDocNumber(clientDTO.getDocNumber());
        client.setLastName(clientDTO.getLastName());
        Client savedClient = clientService.saveClient(client);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "The client has been successfully saved");
        response.put("data", savedClient);

        return ResponseEntity.
                ok().
                body(response);
    }

}
