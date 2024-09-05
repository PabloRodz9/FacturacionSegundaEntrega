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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.persistence.EntityNotFoundException;

@Tag(name = "Products", description = "Endpoints for managing products")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Retrieve all products", description = "Fetches a list of all products")
    @ApiResponse(responseCode = "200", description = "List of products successfully retrieved")
    @GetMapping(produces = "application/json")
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Products:");
        response.put("data", products);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Retrieve a product by ID", description = "Fetches a product by the provided ID")
    @ApiResponse(responseCode = "200", description = "Product successfully retrieved")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Product product = productService.getProductById(id);
            response.put("message", "Product found");
            response.put("data", product);
            return ResponseEntity.ok().body(response);
        } catch (EntityNotFoundException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
    }

    @Operation(summary = "Save a new product", description = "Saves a new product with the provided data")
    @ApiResponse(responseCode = "200", description = "Product successfully saved")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> saveProduct(@RequestBody ProductDTO productDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Product savedProduct = productService.saveProduct(productDTO);
            response.put("message", "Product successfully saved");
            response.put("data", savedProduct);
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by ID")
    @ApiResponse(responseCode = "200", description = "Product successfully deleted")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            productService.deleteProduct(id);
            response.put("message", "Product successfully deleted");
            return ResponseEntity.ok().body(response);
        } catch (EntityNotFoundException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(404).body(response);
        }
    }
}


