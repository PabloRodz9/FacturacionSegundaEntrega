package com.example.facturacion_segunda_entrega.services;
import com.example.facturacion_segunda_entrega.entities.Product;
import com.example.facturacion_segunda_entrega.entities.Sale;
import com.example.facturacion_segunda_entrega.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }


    public boolean deleteSale(int id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
