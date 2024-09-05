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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.persistence.EntityNotFoundException;


@Tag(name = "Clients", description = "Endpoints for managing clients")
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Retrieve all clients", description = "Fetches a list of all clients")
    @ApiResponse(responseCode = "200", description = "List of clients successfully retrieved")
    @GetMapping(produces = "application/json")
    public ResponseEntity<Map<String, Object>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Clients:");
        response.put("data", clients);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Retrieve a client by ID", description = "Fetches a client by the provided ID")
    @ApiResponse(responseCode = "200", description = "Client successfully retrieved")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Map<String, Object>> getClientById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client client = clientService.getClientById(id);
            response.put("message", "Client found");
            response.put("data", client);
            return ResponseEntity.ok().body(response);
        } catch (EntityNotFoundException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
    }

    @Operation(summary = "Save a new client", description = "Saves a new client with the provided data")
    @ApiResponse(responseCode = "200", description = "Client successfully saved")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> saveClient(@RequestBody ClientDTO clientDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client savedClient = clientService.saveClient(clientDTO);
            response.put("message", "Client successfully saved");
            response.put("data", savedClient);
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "Delete a client", description = "Deletes a client by ID")
    @ApiResponse(responseCode = "200", description = "Client successfully deleted")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteClient(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            clientService.deleteClient(id);
            response.put("message", "Client successfully deleted");
            return ResponseEntity.ok().body(response);
        } catch (EntityNotFoundException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
    }
}

