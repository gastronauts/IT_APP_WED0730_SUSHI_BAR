package com.shusi.order.impl;

import com.shusi.meal.model.Meal;
import com.shusi.meal.repository.MealRepository;
import com.shusi.order.OrderService;
import com.shusi.order.OrderedMealService;
import com.shusi.order.model.Order;
import com.shusi.order.model.OrderedMeal;
import com.shusi.order.model.Status;
import com.shusi.order.repository.OrderRepository;
import com.shusi.order.repository.OrderedMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Component
public class OrderedMealImpl implements OrderedMealService {

    @Autowired
    OrderedMealRepository orderedMealRepository;

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Optional<OrderedMeal> getOrdersById(String ordersId) {
        return Optional.ofNullable(orderedMealRepository.findOne(ordersId));
    }

    @Override
    public OrderedMeal addOrders(OrderedMeal orderedMeal) throws IllegalArgumentException {
        Optional<Meal> meal = Optional.ofNullable(mealRepository.findOne(orderedMeal.getMeal().getId()));
        if(meal.isPresent()) {
            if (meal.get().isPossibleToDo()) {
                try {
                    return orderedMealRepository.save(orderedMeal);
                } catch (DataAccessException e) {
                    throw new IllegalArgumentException(e);
                }
            } else
                throw new IllegalArgumentException("Some meal/-s aren't available");
        }else
            throw new IllegalArgumentException("You try order non-existed meal");
    }
}
