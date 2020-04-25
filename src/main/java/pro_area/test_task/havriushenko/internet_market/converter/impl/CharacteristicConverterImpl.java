package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro_area.test_task.havriushenko.internet_market.converter.CharacteristicConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductGroupConverter;
import pro_area.test_task.havriushenko.internet_market.dto.CharacteristicDto;
import pro_area.test_task.havriushenko.internet_market.model.CharacteristicModel;

@Component("characteristicConverter")
public class CharacteristicConverterImpl implements CharacteristicConverter {

    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductGroupConverter productGroupConverter;

    @Override
    public CharacteristicDto convertToDto(CharacteristicModel model) {
        CharacteristicDto characteristic = new CharacteristicDto();
        characteristic.setId(model.getId());
        characteristic.setColor(model.getColor());
        characteristic.setHeight(model.getHeight());
        characteristic.setLength(model.getLength());
        characteristic.setWeight(model.getWeight());
        characteristic.setWidth(model.getWidth());
        return characteristic;
    }

    @Override
    public CharacteristicModel convertToModel(CharacteristicDto characteristic) {
        return null;
    }
}
