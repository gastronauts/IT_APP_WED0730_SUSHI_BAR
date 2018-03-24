package com.shusi.order;

import com.shusi.order.model.Order;
import com.shusi.utilities.MergeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    public OrderService orderService;


    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable Integer id){
        return orderService.getOrderById(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        try {
            return new ResponseEntity<>(orderService.addOrder(order),HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Order> modifyOrder(@RequestBody Order order)
            throws InstantiationException, IllegalAccessException{
        Optional<Order> originalMeal = orderService.getOrderById(order.getId());
        if(!originalMeal.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Order finalOrder = MergeTool.mergeObjects(order,originalMeal.get());
        try {
            return new ResponseEntity<>(orderService.modifyOrder(finalOrder),HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
