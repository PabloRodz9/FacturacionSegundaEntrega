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

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

        @GetMapping(produces = "application/json")
        public ResponseEntity<Map<String, Object>> getAllSales() {
            List<Sale> sales = saleService.getAllSales();

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("message", "Sales:");
            response.put("data", sales);

            return ResponseEntity
                    .ok()
                    .body(response);
        }

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

            return ResponseEntity
                    .ok()
                    .body(response);
        }
}
