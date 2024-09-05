package com.example.facturacion_segunda_entrega.services;
import com.example.facturacion_segunda_entrega.entities.Product;
import com.example.facturacion_segunda_entrega.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
