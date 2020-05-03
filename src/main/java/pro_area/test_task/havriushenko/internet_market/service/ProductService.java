package pro_area.test_task.havriushenko.internet_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro_area.test_task.havriushenko.internet_market.converter.ProductConverter;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;
import pro_area.test_task.havriushenko.internet_market.model.ProductModel;
import pro_area.test_task.havriushenko.internet_market.repository.ProductGroupRerository;
import pro_area.test_task.havriushenko.internet_market.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static pro_area.test_task.havriushenko.internet_market.util.Constans.*;

@Service
public class ProductService {

    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductGroupRerository productGroupRerository;
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductConverter productConverter, ProductGroupRerository productGroupRerository, ProductRepository productRepository) {
        this.productConverter = productConverter;
        this.productGroupRerository = productGroupRerository;
        this.productRepository = productRepository;
    }

    public void addProduct(ProductDto product) {
        productIsNotNull(product);
        productIsPresentByName(product.getName());
        ProductModel productModel = productConverter.convertToModel(product);
        Optional<ProductGroupModel> productGroupModel = productGroupRerository.findByName(product.getGroup().getName());
        if (productGroupModel.isPresent()) {
            productModel.setGroup(productGroupModel.get());
        }
        productRepository.save(productModel);
    }

    private boolean productIsNotNull(ProductDto product) {
        if (Objects.isNull(product)) {
            throw new NullPointerException(MESSAGE_PRODUCT_IS_NULL);
        }
        return true;
    }

    private boolean productIsPresentByName(String name) {
        if (productRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException(MESSAGE_PRODUCT_IS_PRESENT_IN_DATA_BASE);
        }
        return true;
    }

    public void editProduct(ProductDto product) {
        if (!findProductById(product.getId()).isPresent()) {
            throw new IllegalArgumentException(MESSAGE_SUCH_PRODUCT_DOES_NOT_EXIST);
        }
        productRepository.save(productConverter.convertToModel(product));
    }

    public List<ProductDto> getProducts() {
        List<ProductModel> models = productRepository.findAll();
        List<ProductDto> products = models.stream().map(model -> productConverter.convertToDto(model)).collect(Collectors.toList());
        return products;
    }

    public ProductDto getProductById(int id) {
        ProductDto product = productConverter.convertToDto(findProductById(id).get());
        return product;
    }

    public void deleteProduct(int id) {
        Optional<ProductModel> model = findProductById(id);
        if(model.isPresent()){
            productRepository.delete(model.get());
        }
    }

    private Optional<ProductModel> findProductById(int id) {
        return productRepository.findById(id);
    }
}
