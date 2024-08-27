package com.example.facturacion_segunda_entrega.repositories;

import com.example.facturacion_segunda_entrega.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
