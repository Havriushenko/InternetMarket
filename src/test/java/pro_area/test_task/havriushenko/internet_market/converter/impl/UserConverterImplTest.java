package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pro_area.test_task.havriushenko.internet_market.converter.UserConverter;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;
import pro_area.test_task.havriushenko.internet_market.model.Role;
import pro_area.test_task.havriushenko.internet_market.model.UserModel;

import java.util.Collections;

import static org.junit.Assert.*;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.*;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.TEST_USER_PASSWORD;

@RunWith(JUnit4.class)
public class UserConverterImplTest {

    private UserDto userDto;
    private UserModel userModel;

    private UserConverter tested;

    @Before
    public void setUp() {
        createTestedUserModel();
        createTestedUserDto();

        tested = new UserConverterImpl();
    }

    private void createTestedUserModel() {
        userModel = new UserModel();
        userModel.setId(FIRST_TEST_ID);
        userModel.setName(FIRST_TEST_USER_NAME);
        userModel.setSurname(FIRST_TEST_USER_SURNAME);
        userModel.setEmail(FIRST_TEST_USER_EMAIL);
        userModel.setPassword(TEST_USER_PASSWORD);
        userModel.setRole(Collections.singleton(Role.USER));
    }

    private void createTestedUserDto() {
        userDto = new UserDto();
        userDto.setId(FIRST_TEST_ID);
        userDto.setName(FIRST_TEST_USER_NAME);
        userDto.setSurname(FIRST_TEST_USER_SURNAME);
        userDto.setEmail(FIRST_TEST_USER_EMAIL);
        userDto.setPassword(TEST_USER_PASSWORD);
        userDto.setRole(Collections.singleton(Role.USER));
    }

    @Test
    public void convertUserModelToDto() {
        UserDto result = tested.convertToDto(userModel);

        assertEquals(userModel.getEmail(), result.getEmail());
        assertEquals(userDto, result);
    }

    @Test
    public void convertUserDtoToModel() {
        UserModel result = tested.convertToModel(userDto);

        assertEquals(userDto.getSurname(), result.getSurname());
        assertEquals(userModel, result);
    }
}