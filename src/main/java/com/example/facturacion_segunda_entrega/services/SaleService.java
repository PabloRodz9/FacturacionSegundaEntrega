package com.example.facturacion_segunda_entrega.services;
import com.example.facturacion_segunda_entrega.DTO.SaleDTO;
import com.example.facturacion_segunda_entrega.entities.Client;
import com.example.facturacion_segunda_entrega.entities.Product;
import com.example.facturacion_segunda_entrega.entities.Sale;
import com.example.facturacion_segunda_entrega.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    public Sale saveSale(SaleDTO saleDTO) {
        Client client = clientService.getClientById(saleDTO.getClientId());
        if (client == null) {
            throw new IllegalArgumentException("Client not found with id " + saleDTO.getClientId());
        }

        Product product = productService.getProductById(saleDTO.getProductId());
        if (product == null) {
            throw new IllegalArgumentException("Product not found with id " + saleDTO.getProductId());
        }

        if (saleDTO.getQuantity() == null || saleDTO.getQuantity() < 1) {
            throw new IllegalArgumentException("The quantity cannot be null or negative.");
        }

        Sale sale = new Sale();
        sale.setClient(client);
        sale.setProduct(product);
        sale.setSaleDate(saleDTO.getSaleDate());
        sale.setQuantity(saleDTO.getQuantity());

        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(int id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale not found"));
    }

    public void deleteSale(int id) {
        if (!saleRepository.existsById(id)) {
            throw new EntityNotFoundException("Sale not found");
        }
        saleRepository.deleteById(id);
    }
}
