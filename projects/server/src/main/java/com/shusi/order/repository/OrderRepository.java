package com.shusi.order.repository;

import com.shusi.order.model.Order;
import com.shusi.order.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,String>{

    @Query("Select o" +
            " FROM Order o" +
            " JOIN o.table t" +
            " WHERE t.id = :table_id")
    List<Order> getOrdersByTable(@Param("table_id") Integer tableId);

    @Query("Select o" +
            " FROM Order o" +
            " WHERE o.status = :status")
    List<Order> getOrdersByStatus(@Param("status") Status status);
}
