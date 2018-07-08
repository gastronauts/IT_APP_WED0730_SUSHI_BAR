package com.shusi.order.impl;

import com.shusi.meal.repository.MealRepository;
import com.shusi.order.OrderService;
import com.shusi.order.model.Order;
import com.shusi.order.model.OrderedMeal;
import com.shusi.order.model.PairOrderIdStatus;
import com.shusi.order.model.Status;
import com.shusi.order.repository.OrderRepository;
import com.shusi.order.repository.OrderedMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class OrderImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderedMealRepository orderedMealRepository;

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Optional<Order> getOrderById(String orderId) {
        return Optional.ofNullable(orderRepository.findOne(orderId));
    }

    @Override
    public Collection<Order> getOrderByTale(Integer tableId) {
        return orderRepository.getOrdersByTable(tableId);
    }

    @Override
    public Collection<Order> getOrderByStatus(Integer status) {
        return orderRepository.getOrdersByStatus(Status.values()[status]);
    }

    @Override
    public Order addOrder(Order order) throws IllegalArgumentException {
        if(!orderRepository.exists(order.getId())) {
            Collection<OrderedMeal> orderedMeals = order.getMeals();
            orderedMeals.forEach(currentMeal -> currentMeal.getMeal().setPossibleToDo(mealRepository.findOne(currentMeal.getMeal().getId()).isPossibleToDo()));
            if (orderedMeals.stream().allMatch(u -> u.getMeal().isPossibleToDo())) {
                try {
                    order.setDateStart(LocalDateTime.now());
                    if (order.getStatus() == null)
                        order.setStatus(Status.ORDERED);
                    order.getMeals().forEach(o -> o = orderedMealRepository.save(o));
                    return orderRepository.save(order);
                } catch (DataAccessException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            else
                throw new IllegalArgumentException("Some meal/-s aren't available");
        } else
            throw new IllegalArgumentException("Order does already exist");
    }

    @Override
    public Order modifyOrder(Order order) throws IllegalArgumentException, InstantiationException {
        Optional<Order> optionalOrder = Optional.ofNullable(orderRepository.findOne(order.getId()));
        if(optionalOrder.isPresent()){
            Order currentOrder = optionalOrder.get();
            if (currentOrder.getStatus().equals(Status.PREPARING))
                throw new InstantiationException("Order already start preparing");
            if (currentOrder.getStatus().equals(Status.READY))
                throw new InstantiationException("Order is ready");
            if (currentOrder.getStatus().equals(Status.SERVED))
                throw new InstantiationException("Order already served");
            try {
                return orderRepository.save(order);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Order does not exist");
    }

    @Override
    public Order changeOrderStatus(String orderId, Status status) throws IllegalArgumentException {
        Optional<Order> currentOrder = Optional.ofNullable(orderRepository.findOne(orderId));
        if(currentOrder.isPresent()){
            try {
                Order order = currentOrder.get();
                order.setStatus(status);
                if(status.equals(Status.DONE))
                    order.setDateEnd(LocalDateTime.now());
                return orderRepository.save(order);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Order does not exist");
    }

    @Override
    public Collection<PairOrderIdStatus> currentOrderOnTable(Integer tableId) throws IllegalArgumentException {
        Collection<Order> orders = orderRepository.getOrdersByTable(tableId);
        Collection<PairOrderIdStatus> currentOrders = new ArrayList<>();

        orders.stream().filter(order -> !order.getStatus().equals(Status.DONE)).forEach( order ->
                currentOrders.add(  new PairOrderIdStatus(order.getId(),order.getStatus()))
        );
        return currentOrders;
    }
}