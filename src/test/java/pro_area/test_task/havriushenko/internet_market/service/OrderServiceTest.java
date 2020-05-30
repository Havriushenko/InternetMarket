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
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pro_area.test_task.havriushenko.internet_market.util.Constans.MESSAGE_YOUR_ORDER_IS_EMPTY;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.*;

@RunWith(JUnit4.class)
public class OrderServiceTest {

    public static final int TEST_QUANTITY = 10;
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
    private OrderDto closeOrderDto;

    private OrderService tested;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createUserModel();
        createUserDtos();
        createOrderModel();
        createOrderDtos();

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

    private void createUserDtos() {
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

    private void createOrderDtos() {
        orderDto = new OrderDto();
        orderDto.setId(FIRST_TEST_ID);
        orderDto.setUser(userDto);
        orderDto.setStatus(true);
        orderDto.setProducts(Collections.EMPTY_MAP);

        closeOrderDto = new OrderDto();
        closeOrderDto.setId(FIRST_TEST_ID);
        closeOrderDto.setUser(userDto);
        closeOrderDto.setStatus(false);
        closeOrderDto.setProducts(Collections.EMPTY_MAP);
    }

    @Test
    public void findProductByEmail() {
        when(userService.findByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(userDto);
        when(orderRepository.findOrderByUserIdAndStatus(userModel.getId(), true)).thenReturn(Optional.ofNullable(orderModel));
        when(orderConverter.convertToDto(orderModel)).thenReturn(orderDto);

        OrderDto result = tested.getOrder(FIRST_TEST_USER_EMAIL);

        assertEquals(orderDto.getId(), result.getId());
        assertEquals(orderDto, result);
    }

    @Test
    public void throwExceptionWhenUserHasOnlyOrdersInStatusFalse() {
        when(userService.findByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(userDto);

        try {
            tested.getOrder(FIRST_TEST_USER_EMAIL);
        } catch (OrderNotFoundException ex) {
            String result = ex.getMessage();

            assertEquals(MESSAGE_YOUR_ORDER_IS_EMPTY, result);
        }
    }

    @Test
    public void addNewProductToExistingOrder() {
        when(userService.getUserModelByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(userModel);
        when(orderRepository.findOrderByUserIdAndStatus(userDto.getId(), true)).thenReturn(Optional.ofNullable(orderModel));

        tested.putProductToOrder(FIRST_TEST_USER_EMAIL, FIRST_TEST_ID, TEST_QUANTITY);

        verify(orderInfoRepository).upsertOrderInfoByOrderIdAndProductId(orderDto.getId(), FIRST_TEST_ID, TEST_QUANTITY);
    }

    @Test
    public void addNewProductToNewOrder() {
        when(userService.getUserModelByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(userModel);
        when(orderRepository.save(any())).thenReturn(new OrderModel(true, userModel));

        tested.putProductToOrder(FIRST_TEST_USER_EMAIL, FIRST_TEST_ID, TEST_QUANTITY);

        verify(orderRepository).save(any());
        verify(orderInfoRepository).upsertOrderInfoByOrderIdAndProductId(0, FIRST_TEST_ID, TEST_QUANTITY);
    }

    @Test
    public void deleteProductFromOrder() {
        when(userService.getUserModelByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(userModel);
        when(orderRepository.findOrderByUserIdAndStatus(userModel.getId(),true)).thenReturn(Optional.ofNullable(orderModel));

        tested.deleteProductFromOrder(FIRST_TEST_USER_EMAIL, SECOND_TEST_ID);

        verify(orderInfoRepository).deleteByOrderIdAndProductId(orderModel.getId(), SECOND_TEST_ID);
    }

    @Test
    public void getOrderForReceiptAndCloseThisOrder(){
        when(userService.findByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(userDto);
        when(orderRepository.findOrderByUserIdAndStatus(userDto.getId(),true)).thenReturn(Optional.ofNullable(orderModel));
        when(orderConverter.convertToDto(any())).thenReturn(closeOrderDto);

        OrderDto result = tested.getOrderForReceipt(FIRST_TEST_USER_EMAIL);

        assertFalse(result.getStatus());
        verify(orderRepository).save(any());
    }
}
