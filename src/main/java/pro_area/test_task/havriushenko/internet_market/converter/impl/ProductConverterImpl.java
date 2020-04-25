package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro_area.test_task.havriushenko.internet_market.converter.CharacteristicConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductGroupConverter;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.dto.ProductGroupDto;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;
import pro_area.test_task.havriushenko.internet_market.model.ProductModel;

import java.util.HashSet;
import java.util.Set;

@Component("productConverter")
public class ProductConverterImpl implements ProductConverter {

    @Autowired
    private ProductGroupConverter productGroupConverter;
    @Autowired
    private CharacteristicConverter characteristicConverter;

    @Override
    public ProductDto convertToDto(ProductModel model) {
        ProductDto product = new ProductDto();
        product.setId(model.getId());
        product.setName(model.getName());
        product.setPrice(model.getPrice());
        product.setDescription(model.getDescription());
        product.setProductGroup(convertListProductGroups(model.getProductGroup()));
        product.setCharacteristic(characteristicConverter.convertToDto(model.getCharacteristic()));
        return product;
    }

    private Set<ProductGroupDto> convertListProductGroups(Set<ProductGroupModel> models) {
        Set<ProductGroupDto> productGroups = new HashSet<ProductGroupDto>();
        for (ProductGroupModel model : models) {
            productGroups.add(productGroupConverter.convertToDto(model));
        }
        return productGroups;
    }

    @Override
    public ProductModel convertToModel(ProductDto product) {
        ProductModel productModel = new ProductModel();
        return productModel;
    }


}
