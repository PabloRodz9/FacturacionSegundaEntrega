package com.example.facturacion_segunda_entrega.services;

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

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id).orElse(null);
    }


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public boolean deleteClient(int id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
