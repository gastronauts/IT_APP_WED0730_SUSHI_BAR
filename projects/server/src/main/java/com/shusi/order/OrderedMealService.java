package com.shusi.order;

import com.shusi.order.model.OrderedMeal;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderedMealService {

    Optional<OrderedMeal> getOrdersById(String ordersId);

    OrderedMeal addOrders(OrderedMeal orderedMeal) throws IllegalArgumentException;
}
