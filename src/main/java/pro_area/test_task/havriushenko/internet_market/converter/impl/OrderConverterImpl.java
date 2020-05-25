package pro_area.test_task.havriushenko.internet_market.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro_area.test_task.havriushenko.internet_market.converter.OrderConverter;
import pro_area.test_task.havriushenko.internet_market.converter.ProductConverter;
import pro_area.test_task.havriushenko.internet_market.converter.UserConverter;
import pro_area.test_task.havriushenko.internet_market.dto.OrderDto;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.model.OrderInfoKey;
import pro_area.test_task.havriushenko.internet_market.model.OrderInfoModel;
import pro_area.test_task.havriushenko.internet_market.model.OrderModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component("orderConverter")
public class OrderConverterImpl implements OrderConverter {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ProductConverter productConverter;

    public OrderConverterImpl(UserConverter userConverter, ProductConverter productConverter) {
        this.userConverter = userConverter;
        this.productConverter = productConverter;
    }

    @Override
    public OrderDto convertToDto(OrderModel model) {
        OrderDto order = new OrderDto();
        order.setId(model.getId());
        order.setStatus(model.getStatus());
        order.setData(model.getData());
        order.setUser(userConverter.convertToDto(model.getUser()));
        order.setProducts(convertOrderInfoToMap(model.getOrderInfoModels()));
        return order;
    }

    private Map<ProductDto, Integer> convertOrderInfoToMap(Set<OrderInfoModel> orderInfoModelSet) {
        Map<ProductDto, Integer> products = new HashMap<ProductDto, Integer>();
        if(!orderInfoModelSet.isEmpty()){
            for (OrderInfoModel model : orderInfoModelSet) {
                products.put(productConverter.convertToDto(model.getProduct()), model.getQuantity());
            }
        }
        return products;
    }

    @Override
    public OrderModel convertToModel(OrderDto order) {
        OrderModel model = new OrderModel();
        model.setId(order.getId());
        model.setUser(userConverter.convertToModel(order.getUser()));
        model.setStatus(order.getStatus());
        model.setOrderInfoModels(convertOrderInfoToSet(order.getProducts(), order));
        return model;
    }

    private Set<OrderInfoModel> convertOrderInfoToSet(Map<ProductDto, Integer> products, OrderDto order) {
        Set<OrderInfoModel> orderInfoModels = new HashSet<OrderInfoModel>();
        if(!products.isEmpty()){
            for (Map.Entry<ProductDto, Integer> model : products.entrySet()) {
                OrderInfoModel orderInfoModel = new OrderInfoModel();
                orderInfoModel.setOrderInfoKey(relationOrderInfoKey(order, model.getKey()));
                orderInfoModel.setProductId(productConverter.convertToModel(model.getKey()));
                orderInfoModels.add(orderInfoModel);
            }
        }
        return orderInfoModels;
    }

    private OrderInfoKey relationOrderInfoKey(OrderDto order, ProductDto product) {
        OrderInfoKey orderInfoKey = new OrderInfoKey();
        orderInfoKey.setOrderId(order.getId());
        orderInfoKey.setProductId(product.getId());
        return orderInfoKey;
    }
}
