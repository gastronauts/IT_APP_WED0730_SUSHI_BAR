package com.shusi.order;

import com.shusi.order.model.Order;
import com.shusi.order.model.PairOrderIdStatus;
import com.shusi.order.model.Status;
import com.shusi.utilities.MergeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @Autowired
    public OrderedMealService orderedMealService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable String id){
        return orderService.getOrderById(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "table/{id}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> getOrderByTable(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getOrderByTale(id));
    }

    @RequestMapping(value = "table/current/{id}", method = RequestMethod.GET)
    public ResponseEntity<Collection<PairOrderIdStatus>> getCurrentOrderOnTable(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.currentOrderOnTable(id));
    }

    @RequestMapping(value = "status/{status}",method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> getOrderByStatus(@PathVariable Integer status){
        return ResponseEntity.ok(orderService.getOrderByStatus(status));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        try {
            return new ResponseEntity<>(orderService.addOrder(order),HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> modifyOrder(@RequestBody Order order)
            throws InstantiationException, IllegalAccessException{
        Optional<Order> originalMeal = orderService.getOrderById(order.getId());
        if(!originalMeal.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            order.getMeals().stream().forEach(o -> o = orderedMealService.addOrders(o));
            Order finalOrder = MergeTool.mergeObjects(order,originalMeal.get());
            return new ResponseEntity<>(orderService.modifyOrder(finalOrder),HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (InstantiationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "preparing/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> preparingOrder(@PathVariable String id){
        return changeOrder(id,Status.PREPARING);
    }

    @RequestMapping(value = "ready/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> readyOrder(@PathVariable String id){
        return changeOrder(id,Status.READY);
    }

    @RequestMapping(value = "served/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> servedOrder(@PathVariable String id){
        return changeOrder(id,Status.SERVED);
    }

    @RequestMapping(value = "done/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> doneOrder(@PathVariable String id){
        return changeOrder(id,Status.DONE);
    }

    private ResponseEntity<Order> changeOrder(String orderId, Status status){
        Optional<Order> optionalOrder = orderService.getOrderById(orderId);
        if(!optionalOrder.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(!(optionalOrder.get().getStatus().ordinal() == (status.ordinal()-1)))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            return new ResponseEntity<>(orderService.changeOrderStatus(orderId,status),HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}