package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro_area.test_task.havriushenko.internet_market.converter.ProductConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductGroupConverter;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

@Component("productConverter")
public class ProductConverterImpl implements ProductConverter {

    @Autowired
    private ProductGroupConverter productGroupConverter;

    @Override
    public ProductDto convertToDto(ProductModel model) {
        ProductDto product = new ProductDto();
        product.setId(model.getId());
        product.setName(model.getName());
        product.setPrice(model.getPrice());
        product.setDescription(model.getDescription());
        product.setProductGroup(productGroupConverter.convertToDto(model.getProductGroup()));
        return product;
    }

    @Override
    public ProductModel convertToModel(ProductDto product) {
        ProductModel model = new ProductModel();
        model.setId(product.getId());
        model.setName(product.getName());
        model.setPrice(product.getPrice());
        model.setDescription(product.getDescription());
        model.setProductGroup(productGroupConverter.convertToModel(product.getProductGroup()));
        return model;
    }


}
