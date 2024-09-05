package com.example.facturacion_segunda_entrega.services;
import com.example.facturacion_segunda_entrega.DTO.ProductDTO;
import com.example.facturacion_segunda_entrega.entities.Product;
import com.example.facturacion_segunda_entrega.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public Product saveProduct(ProductDTO productDTO) {
        if (productDTO.getDescription() == null || productDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("The description cannot be null or empty.");
        }
        if (productDTO.getPrice() == null || productDTO.getPrice() < 0) {
            throw new IllegalArgumentException("The price cannot be null or negative.");
        }
        if (productDTO.getStock() == null || productDTO.getStock() < 0) {
            throw new IllegalArgumentException("The stock cannot be null or negative.");
        }

        Product product = new Product();
        product.setCode(productDTO.getCode());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}

