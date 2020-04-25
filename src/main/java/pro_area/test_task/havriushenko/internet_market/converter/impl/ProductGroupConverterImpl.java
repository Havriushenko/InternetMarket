package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro_area.test_task.havriushenko.internet_market.converter.CharacteristicConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductGroupConverter;
import pro_area.test_task.havriushenko.internet_market.dto.ProductGroupDto;
import pro_area.test_task.havriushenko.internet_market.model.ProductGroupModel;

@Component("productGroupConverter")
public class ProductGroupConverterImpl implements ProductGroupConverter {

    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private CharacteristicConverter characteristicConverter;

    @Override
    public ProductGroupDto convertToDto(ProductGroupModel models) {
        ProductGroupDto productGroup = new ProductGroupDto();
        productGroup.setId(models.getId());
        productGroup.setGroup(models.getGroup());
        return productGroup;
    }

    @Override
    public ProductGroupModel convertToModel(ProductGroupDto productGroup) {
        return null;
    }
}
