package ag.selm.manager.service;

import ag.selm.manager.entity.Product;
import ag.selm.manager.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return this.productRepo.findById(id);
    }

    @Override
    public Product createProduct(String name, String details, BigDecimal price) {
        return this.productRepo.addProduct(
                new Product(
                        null,
                        name,
                        details,
                        price
                ));
    }

    @Override
    public void deleteProduct(int id) {
        this.productRepo.deleteById(id);
    }

    @Override
    public void updateProduct(int id, String name, String details, BigDecimal price) {
        Product product = new Product(id, name, details, price);
        this.productRepo.updateProduct(product);
    }
}