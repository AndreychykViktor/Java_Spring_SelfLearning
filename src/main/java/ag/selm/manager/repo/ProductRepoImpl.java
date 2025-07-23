package ag.selm.manager.repo;

import ag.selm.manager.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class ProductRepoImpl implements ProductRepo {
    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    public ProductRepoImpl() {
        IntStream.range(1, 4)
                .forEach(i -> {
                    this.products.add(new Product(
                            i,
                            "Product " + i,
                            "Details for product " + i,
                            BigDecimal.valueOf(100.0 * i)
                    ));
                });
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product findById(int id) {
        return products.stream().
                filter(product -> product.getId().equals(id)).
                findFirst().
                orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        product.setId(this.products.stream()
                              .max(Comparator.comparingInt(Product::getId))
                              .map(Product::getId)
                              .orElse(0) + 1);

        if (product == null) {
            throw new IllegalArgumentException("Product and its ID must not be null");
        }

        products.add(product);
        return product;
    }

    @Override
    public void deleteById(int id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                products.set(i, product);
            }
        }
    }
}