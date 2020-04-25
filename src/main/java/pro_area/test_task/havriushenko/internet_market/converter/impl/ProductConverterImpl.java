package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.springframework.stereotype.Component;
import pro_area.test_task.havriushenko.internet_market.converter.ProductConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductGroupConverter;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

@Component("productConverter")
public class ProductConverterImpl implements ProductConverter {

    private ProductGroupConverter productGroupConverter;

    public ProductConverterImpl() {
    }

    public ProductConverterImpl(ProductGroupConverter productGroupConverter) {
        this.productGroupConverter = productGroupConverter;
    }

    @Override
    public ProductDto convertToDto(ProductModel model) {
        ProductDto product = new ProductDto();
        product.setId(model.getId());
        product.setName(model.getName());
        product.setPrice(model.getPrice());
        product.setDescription(model.getDescription());
        product.setGroup(productGroupConverter.convertToDto(model.getGroup()));
        return product;
    }

    @Override
    public ProductModel convertToModel(ProductDto product) {
        ProductModel model = new ProductModel();
        model.setId(product.getId());
        model.setName(product.getName());
        model.setPrice(product.getPrice());
        model.setDescription(product.getDescription());
        model.setGroup(productGroupConverter.convertToModel(product.getGroup()));
        return model;
    }
}
