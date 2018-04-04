package com.shusi.order.repository;

import com.shusi.order.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,String>{
}
