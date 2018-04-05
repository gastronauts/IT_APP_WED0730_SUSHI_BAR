package com.shusi.order;

import com.shusi.order.model.Order;
import com.shusi.order.model.Status;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface OrderService {

    Optional<Order> getOrderById(String orderId);

    Collection<Order> getOrderByTale(Integer tableId);

    Order addOrder(Order order) throws IllegalArgumentException;

    Order modifyOrder(Order order) throws IllegalArgumentException, InstantiationException;

    Order changeOrderStatus(String orderId, Status status)throws IllegalArgumentException;
}
