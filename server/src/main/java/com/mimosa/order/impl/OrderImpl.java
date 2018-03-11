package com.mimosa.order.impl;

import com.mimosa.order.OrderService;
import com.mimosa.order.model.Order;
import com.mimosa.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Optional<Order> getOrderById(Integer orderId) {
        return Optional.ofNullable(orderRepository.findOne(orderId));
    }

    @Override
    public Order addOrder(Order order) throws IllegalArgumentException {
        try {
            return orderRepository.save(order);
        }
        catch (DataAccessException e){
            throw  new IllegalArgumentException(e);
        }
    }

    @Override
    public Order modifyOrder(Order order) throws IllegalArgumentException {
        if(orderRepository.exists(order.getId())){
            try {
                return orderRepository.save(order);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Order does not exist");
    }
}
