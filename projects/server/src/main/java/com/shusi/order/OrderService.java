package com.shusi.order;

import com.shusi.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderService {

    Optional<Order> getOrderById(Integer orderId);

    Order addOrder(Order order) throws IllegalArgumentException;

    Order modifyOrder(Order order) throws IllegalArgumentException;
}
