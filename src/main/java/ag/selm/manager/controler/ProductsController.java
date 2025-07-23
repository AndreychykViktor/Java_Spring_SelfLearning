package ag.selm.manager.controler;

import ag.selm.manager.controler.payload.NewProductPayload;
import ag.selm.manager.controler.payload.UpdateProductPayload;
import ag.selm.manager.entity.Product;
import ag.selm.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductService productService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getProductsList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String createProductForm() {
        return "catalogue/products/product_create";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload) {
        Product createdProduct = productService.createProduct(payload.name(), payload.details(), payload.price());
        return "redirect:/catalogue/products/" + createdProduct.getId();

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getProductDetails(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/catalogue/products/list?error=notfound";
        }
        model.addAttribute("product", product);
        return "catalogue/products/product-details";
    }
//http://localhost:8080/catalogue/products/4/edit
    @GetMapping("{id}/edit")
    public String editProductForm(@PathVariable("id") int id, Model model) {
        System.out.println("Product tut");

        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/catalogue/products/list?error=notfound";


        }
        model.addAttribute("product", product);
        return "catalogue/products/product_edit";
    }

    @PostMapping("{id}/edit")
    public String updateProduct(@PathVariable("id") int id, UpdateProductPayload payload) {
        Product product = productService.getProductById(id);
        if (product == null) {
            System.out.println("Product not found");
            return "redirect:/catalogue/products/list?error=notfound";
        }

        productService.updateProduct(id, payload.name(), payload.details(), payload.price());
        return "redirect:/catalogue/products/" + id;

    }

    @PostMapping("{id}/delete")
    public String deleteProduct(@PathVariable("id") int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/catalogue/products/list?error=notfound";
        }
        productService.deleteProduct(id);
        return "redirect:/catalogue/products/list?error=notfound";
    }
}