package com.shusi.order.repository;

import com.shusi.order.model.Order;
import com.shusi.order.model.OrderedMeal;
import com.shusi.order.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderedMealRepository extends CrudRepository<OrderedMeal,String>{

}
