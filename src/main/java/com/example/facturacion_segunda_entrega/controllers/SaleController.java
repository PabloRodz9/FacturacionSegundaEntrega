package com.example.facturacion_segunda_entrega.controllers;

import com.example.facturacion_segunda_entrega.entities.Sale;
import com.example.facturacion_segunda_entrega.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Map<String, Object>> getAllClients() {
        List<Sale> sales = saleService.getAllSales();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Sales:");
        response.put("data", sales);

        return ResponseEntity
                .ok()
                .body(response);
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> saveSale(@RequestBody Sale sale) {
        Sale savedSaled = saleService.saveSale(sale);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "The sale has been successfully saved");
        response.put("data", savedSaled);

        return ResponseEntity.
                ok().
                body(response);
    }

}
