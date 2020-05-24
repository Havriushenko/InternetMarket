package pro_area.test_task.havriushenko.internet_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro_area.test_task.havriushenko.internet_market.converter.OrderConverter;
import pro_area.test_task.havriushenko.internet_market.dto.OrderDto;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;
import pro_area.test_task.havriushenko.internet_market.exception.OrderNotFoundException;
import pro_area.test_task.havriushenko.internet_market.model.OrderModel;
import pro_area.test_task.havriushenko.internet_market.model.UserModel;
import pro_area.test_task.havriushenko.internet_market.repository.OrderInfoRepository;
import pro_area.test_task.havriushenko.internet_market.repository.OrderRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static pro_area.test_task.havriushenko.internet_market.util.Constans.*;

@Service
public class OrderService {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private OrderInfoRepository orderInfoRepository;

    public OrderService(UserService userService, OrderRepository orderRepository, OrderConverter orderConverter, OrderInfoRepository orderInfoRepository) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.orderInfoRepository = orderInfoRepository;
    }

    public OrderDto getOrder(String email) {
        UserDto user = userService.findByEmail(email);
        Optional<OrderModel> model = orderRepository.findOrderByUserIdAndStatus(user.getId(), true);
        if (model.isPresent()) {
            return orderConverter.convertToDto(model.get());
        }
        throw new OrderNotFoundException(MESSAGE_YOUR_ORDER_IS_EMPTY);
    }

    public void putProductToOrder(String email, int productId, int quantity) {
        UserModel user = userService.getUserModelByEmail(email);
        OrderModel order = orderRepository.findOrderByUserIdAndStatus(user.getId(), true)
                .orElseGet(() -> orderRepository.save(new OrderModel(true, user)));

        orderInfoRepository.upsertOrderInfoByOrderIdAndProductId(order.getId(), productId, quantity);
    }

    public void deleteProductFromOrder(String email, int productId) {
        UserModel user = userService.getUserModelByEmail(email);
        orderRepository.findOrderByUserIdAndStatus(user.getId(), true)
                .ifPresent(order -> orderInfoRepository.deleteByOrderIdAndProductId(order.getId(), productId));
    }

    public OrderDto getOrderForReceipt(String email) {
        UserDto user = userService.findByEmail(email);
        Optional<OrderModel> model = orderRepository.findOrderByUserIdAndStatus(user.getId(),true);
        if(model.isPresent()){
            model.get().setStatus(false);
            model.get().setData(DateTimeFormatter.ofPattern(PATTERN_FOR_DATE).format(LocalDateTime.now()));
            orderRepository.save(model.get());
            return orderConverter.convertToDto(model.get());
        }
        throw new OrderNotFoundException(MESSAGE_YOUR_ORDER_IS_EMPTY);
    }
}
