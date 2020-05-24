package pro_area.test_task.havriushenko.internet_market.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pro_area.test_task.havriushenko.internet_market.dto.OrderDto;
import pro_area.test_task.havriushenko.internet_market.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static pro_area.test_task.havriushenko.internet_market.util.Constans.*;
import static pro_area.test_task.havriushenko.internet_market.util.SecurityConstants.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController() {
    }

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public OrderDto getOrder(HttpServletRequest req) {
        return orderService.getOrder(getUserFromToken(req));
    }

    @PutMapping("/{product_id}/{quantity}")
    public void putProductInOrder(@PathVariable int product_id, @PathVariable int quantity, HttpServletRequest req) {
        orderService.putProductToOrder(getUserFromToken(req), product_id, quantity);
    }

    @DeleteMapping("/{product_id}")
    public void deleteProductFromOrder(@PathVariable int product_id, HttpServletRequest req) {
        orderService.deleteProductFromOrder(getUserFromToken(req), product_id);
    }

    @GetMapping("/receipt")
    public ModelAndView getReceipt(HttpServletRequest req) {
        return new ModelAndView(GENERATE_RECEIPT_ODF_BEAN_NAME, KEY_ORDER_FOR_MAP_PDF, orderService.getOrderForReceipt(getUserFromToken(req)));
    }

    private String getUserFromToken(HttpServletRequest req) {
        String token = req.getHeader(HEADER_STRING);
        if (StringUtils.isNotEmpty(token)) {

            String user = JWT.require(Algorithm.HMAC512(SECURITY_SECRET_WORD.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
            if (StringUtils.isNotEmpty(user)) {
                return user;
            }
        }
        throw new UsernameNotFoundException(MESSAGE_USER_WAS_NOT_FOUND_EXCEPTION);
    }
}
