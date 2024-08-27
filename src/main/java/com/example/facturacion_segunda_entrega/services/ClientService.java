package com.example.facturacion_segunda_entrega.services;

import com.example.facturacion_segunda_entrega.entities.Client;
import com.example.facturacion_segunda_entrega.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

}
