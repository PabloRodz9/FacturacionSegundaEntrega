package com.example.facturacion_segunda_entrega.repositories;

import com.example.facturacion_segunda_entrega.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
