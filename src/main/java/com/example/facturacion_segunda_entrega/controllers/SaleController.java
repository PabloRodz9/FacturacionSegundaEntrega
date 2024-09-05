package com.example.facturacion_segunda_entrega.controllers;

import com.example.facturacion_segunda_entrega.DTO.SaleDTO;
import com.example.facturacion_segunda_entrega.entities.Sale;
import com.example.facturacion_segunda_entrega.services.ClientService;
import com.example.facturacion_segunda_entrega.services.ProductService;
import com.example.facturacion_segunda_entrega.services.SaleService;
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

@Tag(name = "Sales", description = "Endpoints for managing sales")
@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "Retrieve all sales", description = "Fetches a list of all sales")
    @ApiResponse(responseCode = "200", description = "List of sales successfully retrieved")
    @GetMapping(produces = "application/json")
    public ResponseEntity<Map<String, Object>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Sales:");
        response.put("data", sales);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Retrieve a sale by ID", description = "Fetches a sale by the provided ID")
    @ApiResponse(responseCode = "200", description = "Sale successfully retrieved")
    @ApiResponse(responseCode = "404", description = "Sale not found")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Map<String, Object>> getSaleById(@PathVariable int id) {
        Sale sale = saleService.getSaleById(id);

        Map<String, Object> response = new HashMap<>();
        if (sale != null) {
            response.put("message", "Sale found");
            response.put("data", sale);
            return ResponseEntity.ok().body(response);
        } else {
            response.put("message", "Sale not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @Operation(summary = "Save a new sale", description = "Saves a new sale with the provided data")
    @ApiResponse(responseCode = "200", description = "Sale successfully saved")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> saveSale(@RequestBody SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setClient(clientService.getClientById(saleDTO.getClientId()));
        sale.setProduct(productService.getProductById(saleDTO.getProductId()));
        sale.setSaleDate(saleDTO.getSaleDate());
        sale.setQuantity(saleDTO.getQuantity());
        Sale savedSale = saleService.saveSale(sale);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "The sale has been successfully saved");
        response.put("data", savedSale);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete a sale", description = "Deletes a sale by ID")
    @ApiResponse(responseCode = "200", description = "Sale successfully deleted")
    @ApiResponse(responseCode = "404", description = "Sale not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteSale(@PathVariable int id) {
        boolean isDeleted = saleService.deleteSale(id);

        Map<String, Object> response = new HashMap<>();
        if (isDeleted) {
            response.put("message", "Sale successfully deleted");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("message", "Sale not found");
            return ResponseEntity.status(404).body(response);
        }
    }

}
