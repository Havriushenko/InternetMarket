package pro_area.test_task.havriushenko.internet_market.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro_area.test_task.havriushenko.internet_market.converter.OrderConverter;
import pro_area.test_task.havriushenko.internet_market.dto.OrderDto;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;
import pro_area.test_task.havriushenko.internet_market.exception.OrderNotFoundException;
import pro_area.test_task.havriushenko.internet_market.model.OrderModel;
import pro_area.test_task.havriushenko.internet_market.model.Role;
import pro_area.test_task.havriushenko.internet_market.model.UserModel;
import pro_area.test_task.havriushenko.internet_market.repository.OrderInfoRepository;
import pro_area.test_task.havriushenko.internet_market.repository.OrderRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static pro_area.test_task.havriushenko.internet_market.util.Constans.MESSAGE_YOUR_ORDER_IS_EMPTY;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.*;

@RunWith(JUnit4.class)
public class OrderServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderConverter orderConverter;
    @Mock
    private OrderInfoRepository orderInfoRepository;

    private UserModel userModel;
    private UserDto userDto;
    private OrderModel orderModel;
    private OrderDto orderDto;

    private OrderService tested;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createUserModel();
        createUserDto();
        createOrderModel();
        createOrderDto();

        tested = new OrderService(userService, orderRepository, orderConverter, orderInfoRepository);
    }

    private void createUserModel() {
        userModel = new UserModel();
        userModel.setId(FIRST_TEST_ID);
        userModel.setName(FIRST_TEST_USER_NAME);
        userModel.setSurname(FIRST_TEST_USER_SURNAME);
        userModel.setEmail(FIRST_TEST_USER_EMAIL);
        userModel.setPassword(TEST_USER_PASSWORD);
        userModel.setRole(Collections.singleton(Role.USER));
    }

    private void createUserDto() {
        userDto = new UserDto();
        userDto.setId(FIRST_TEST_ID);
        userDto.setName(FIRST_TEST_USER_NAME);
        userDto.setSurname(FIRST_TEST_USER_SURNAME);
        userDto.setEmail(FIRST_TEST_USER_EMAIL);
        userDto.setPassword(TEST_USER_PASSWORD);
        userDto.setRole(Collections.singleton(Role.USER));
    }

    private void createOrderModel() {
        orderModel = new OrderModel();
        orderModel.setId(FIRST_TEST_ID);
        orderModel.setUser(userModel);
        orderModel.setStatus(true);
        orderModel.setOrderInfoModels(Collections.EMPTY_SET);
    }

    private void createOrderDto() {
        orderDto = new OrderDto();
        orderDto.setId(FIRST_TEST_ID);
        orderDto.setUser(userDto);
        orderDto.setStatus(true);
        orderModel.setOrderInfoModels(Collections.EMPTY_SET);
    }

    @Test
    public void findOrderWhereUserEmailIsFirstUserEmail() {
        when(userService.findByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(userDto);
        when(orderRepository.findOrderByUserIdAndStatus(userModel.getId(), true)).thenReturn(Optional.ofNullable(orderModel));
        when(orderConverter.convertToDto(orderModel)).thenReturn(orderDto);

        OrderDto result = tested.getOrder(FIRST_TEST_USER_EMAIL);

        assertEquals(orderDto.getId(), result.getId());
        assertEquals(orderDto, result);
    }

    @Test
    public void getOrderByFirstUserEmailWhereUserHasAllOrdersInStatusFalse() {
        when(userService.findByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(userDto);

        try {
            tested.getOrder(FIRST_TEST_USER_EMAIL);
        } catch (OrderNotFoundException ex) {
            String result = ex.getMessage();

            assertEquals(MESSAGE_YOUR_ORDER_IS_EMPTY, result);
        }
    }
}