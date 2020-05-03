package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.springframework.stereotype.Component;
import pro_area.test_task.havriushenko.internet_market.converter.UserConverter;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;
import pro_area.test_task.havriushenko.internet_market.model.UserModel;

@Component("userConverter")
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDto convertToDto(UserModel model) {
        UserDto user = new UserDto();
        user.setId(model.getId());
        user.setName(model.getName());
        user.setSurname(model.getSurname());
        user.setPassword(model.getPassword());
        user.setRole(model.getRole());
        user.setEmail(model.getEmail());
        return user;
    }

    @Override
    public UserModel convertToModel(UserDto user) {
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setName(user.getName());
        model.setSurname(user.getSurname());
        model.setPassword(user.getPassword());
        model.setRole(user.getRole());
        model.setEmail(user.getEmail());
        return model;
    }
}
