package com.mimosa.order;

import com.mimosa.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderService {

    Optional<Order> getOrderById(Integer orderId);

    Order addOrder(Order order) throws IllegalArgumentException;

    Order modifyOrder(Order order) throws IllegalArgumentException;
}
