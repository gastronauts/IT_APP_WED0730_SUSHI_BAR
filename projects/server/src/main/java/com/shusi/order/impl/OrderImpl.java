package com.shusi.order.impl;

import com.shusi.ingredient.model.Ingredient;
import com.shusi.meal.model.Meal;
import com.shusi.meal.repository.MealRepository;
import com.shusi.order.OrderService;
import com.shusi.order.model.Order;
import com.shusi.order.model.Status;
import com.shusi.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

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
            Collection<Meal> orderedMeals = order.getMeals();
            orderedMeals.forEach(currentMeal -> currentMeal.setPossibleToDo(mealRepository.findOne(currentMeal.getId()).isPossibleToDo()));
            if (orderedMeals.stream().allMatch(Meal::isPossibleToDo)) {
                try {
                    if (order.getStatus() == null)
                        order.setStatus(Status.ORDERED);
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
                return orderRepository.save(order);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Order does not exist");
    }
}
