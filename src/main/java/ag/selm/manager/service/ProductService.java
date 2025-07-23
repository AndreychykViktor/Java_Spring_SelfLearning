package ag.selm.manager.service;

import ag.selm.manager.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
    Product createProduct(String name, String details, BigDecimal price);
    void deleteProduct(int id);
    void updateProduct(int id, String name, String details, BigDecimal price);
}