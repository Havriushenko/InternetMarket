package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro_area.test_task.havriushenko.internet_market.converter.OrderConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductConverter;
import pro_area.test_task.havriushenko.internet_market.converter.UserConverter;
import pro_area.test_task.havriushenko.internet_market.dto.OrderDto;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;
import pro_area.test_task.havriushenko.internet_market.model.OrderModel;
import pro_area.test_task.havriushenko.internet_market.model.Role;
import pro_area.test_task.havriushenko.internet_market.model.UserModel;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.*;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.TEST_USER_PASSWORD;

@RunWith(JUnit4.class)
public class OrderConverterImplTest {

    @Mock
    private UserConverter userConverter;
    @Mock
    private ProductConverter productConverter;

    private OrderDto orderDto;
    private OrderModel orderModel;
    private UserModel userModel;
    private UserDto userDto;
    private Set orderInfoModels = new HashSet();
    private Map orderInfoDtos = new HashMap();

    private OrderConverter tested;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createTestUserDto();
        createTestUserModel();
        createTestOrderModel();
        createTestOrderDto();

        tested = new OrderConverterImpl(userConverter, productConverter);
    }

    private void createTestUserModel() {
        userModel = new UserModel();
        userModel.setId(FIRST_TEST_ID);
        userModel.setName(FIRST_TEST_USER_NAME);
        userModel.setSurname(FIRST_TEST_USER_SURNAME);
        userModel.setEmail(FIRST_TEST_USER_EMAIL);
        userModel.setPassword(TEST_USER_PASSWORD);
        userModel.setRole(Collections.singleton(Role.USER));
    }

    private void createTestUserDto() {
        userDto = new UserDto();
        userDto.setId(FIRST_TEST_ID);
        userDto.setName(FIRST_TEST_USER_NAME);
        userDto.setSurname(FIRST_TEST_USER_SURNAME);
        userDto.setEmail(FIRST_TEST_USER_EMAIL);
        userDto.setPassword(TEST_USER_PASSWORD);
        userDto.setRole(Collections.singleton(Role.USER));
    }

    private void createTestOrderModel() {
        orderModel = new OrderModel();
        orderModel.setId(FIRST_TEST_ID);
        orderModel.setUser(userModel);
        orderModel.setOrderInfoModels(orderInfoModels);
        orderModel.setStatus(true);
    }

    private void createTestOrderDto() {
        orderDto = new OrderDto();
        orderDto.setId(FIRST_TEST_ID);
        orderDto.setUser(userDto);
        orderDto.setStatus(true);
        orderDto.setProducts(orderInfoDtos);
    }

    @Test
    public void convertOrderModelToDto() {
        when(userConverter.convertToDto(userModel)).thenReturn(userDto);

        OrderDto result = tested.convertToDto(orderModel);

        assertEquals(orderModel.getUser().getEmail(), result.getUser().getEmail());
        assertEquals(orderDto, result);
    }

    @Test
    public void convertOrderDtoToModel() {
        when(userConverter.convertToModel(userDto)).thenReturn(userModel);

        OrderModel result = tested.convertToModel(orderDto);

        assertEquals(orderDto.getUser().getSurname(), result.getUser().getSurname());
        assertEquals(orderModel, result);
    }
}