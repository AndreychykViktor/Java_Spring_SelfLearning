package ag.selm.manager.repo;

import ag.selm.manager.entity.Product;

import java.util.List;

public interface ProductRepo {
    List<Product> findAll();
    Product findById(int id);
    Product addProduct(Product product);
    void deleteById(int id);
    void updateProduct(Product product);
}