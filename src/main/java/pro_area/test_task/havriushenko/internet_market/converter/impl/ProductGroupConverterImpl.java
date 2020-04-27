package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.springframework.stereotype.Component;
import pro_area.test_task.havriushenko.internet_market.converter.ProductGroupConverter;
import pro_area.test_task.havriushenko.internet_market.dto.ProductGroupDto;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;

@Component("productGroupConverter")
public class ProductGroupConverterImpl implements ProductGroupConverter {

    @Override
    public ProductGroupDto convertToDto(ProductGroupModel model) {
        ProductGroupDto productGroup = new ProductGroupDto();
        productGroup.setId(model.getId());
        productGroup.setGroup(model.getName());
        return productGroup;
    }



    @Override
    public ProductGroupModel convertToModel(ProductGroupDto productGroup) {
        ProductGroupModel model = new ProductGroupModel();
        model.setId(productGroup.getId());
        model.setGroup(productGroup.getName());
        return model;
    }
}
