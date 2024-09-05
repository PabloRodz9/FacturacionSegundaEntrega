package com.example.facturacion_segunda_entrega.services;

import com.example.facturacion_segunda_entrega.DTO.ClientDTO;
import com.example.facturacion_segunda_entrega.entities.Client;
import com.example.facturacion_segunda_entrega.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public Client saveClient(ClientDTO clientDTO) {
        if (clientDTO.getName() == null || clientDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }
        if (clientDTO.getLastName() == null || clientDTO.getLastName().isEmpty()) {
            throw new IllegalArgumentException("The last name cannot be null or empty.");
        }

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setDocNumber(clientDTO.getDocNumber());

        return clientRepository.save(client);
    }

    public void deleteClient(int id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client not found");
        }
        clientRepository.deleteById(id);
    }
}

