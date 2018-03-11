package com.mimosa.order.repository;

import com.mimosa.order.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Integer>{
}
