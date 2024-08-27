package com.example.facturacion_segunda_entrega.repositories;

import com.example.facturacion_segunda_entrega.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
