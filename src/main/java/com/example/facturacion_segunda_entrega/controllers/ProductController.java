package com.example.facturacion_segunda_entrega.controllers;

import com.example.facturacion_segunda_entrega.DTO.ProductDTO;
import com.example.facturacion_segunda_entrega.entities.Product;
import com.example.facturacion_segunda_entrega.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Map<String, Object>> getAllClients() {
        List<Product> products = productService.getAllProducts();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Products:");
        response.put("data", products);

        return ResponseEntity
                .ok()
                .body(response);
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> saveClient(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setCode(productDTO.getCode());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        Product savedProduct = productService.saveProduct(product);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "The product has been successfully saved");
        response.put("data", product);

        return ResponseEntity.
                ok().
                body(response);
    }

}
