package pro_area.test_task.havriushenko.internet_market.controller;

import org.springframework.web.bind.annotation.*;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @PutMapping("/{id}")
    public void editProduct(@PathVariable int id, @RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
    }

    @GetMapping("/getProducts")
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
    }

    @GetMapping("/getProductById/{id}")
    public ProductDto getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }
}
