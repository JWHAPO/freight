package app.hapo.car.freight.api.order;

import app.hapo.car.freight.domain.order.Order;
import app.hapo.car.freight.domain.order.OrderStatus;
import app.hapo.car.freight.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * freight
 * Class: OrderController
 * Created by hapo on 2019-01-25.
 * Description: OrderController
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    private final OrderResourceAssembler orderResourceAssembler;

    public OrderController(OrderResourceAssembler orderResourceAssembler) {
        this.orderResourceAssembler = orderResourceAssembler;
    }

    @GetMapping(value = "/orders")
    public Resources<Resource<Order>> all(){
        List<Resource<Order>> orders = orderService.findAll().stream()
                .map(orderResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orders,
                linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping(value = "/orders/{id}")
    public Resource<Order> findById(@PathVariable Long id){
        return orderResourceAssembler.toResource(
                orderService.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id))
        );
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<Resource<Order>> newOrder(@RequestBody Order order){
        order.setStatus(OrderStatus.IN_PROGRESS);
        Order newOrder = orderService.save(order).get();

        return ResponseEntity
                .created(linkTo(methodOn(OrderController.class).findById(newOrder.getOrderId())).toUri())
                .body(orderResourceAssembler.toResource(newOrder));
    }

    @DeleteMapping("/orders/{id}/cancel")
    public ResponseEntity<ResourceSupport> cancel(@PathVariable Long id){
        Order order = orderService.findById(id).orElseThrow(()->new OrderNotFoundException(id));

        if(order.getStatus() == OrderStatus.IN_PROGRESS){
            order.setStatus(OrderStatus.CANCELLED);
            return ResponseEntity.ok(orderResourceAssembler.toResource(orderService.save(order).get()));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't cancel an order that is in the " + order.getStatus() + " status"));
    }

    @PutMapping("orders/{id}/complete")
    public ResponseEntity<ResourceSupport> complete(@PathVariable Long id){
        Order order = orderService.findById(id).orElseThrow(()->new OrderNotFoundException(id));

        if(order.getStatus() == OrderStatus.IN_PROGRESS){
            order.setStatus(OrderStatus.COMPLETED);
            return ResponseEntity.ok(orderResourceAssembler.toResource(orderService.save(order).get()));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't complete an order that is in the " + order.getStatus() + " status"));
    }

    class OrderNotFoundException extends RuntimeException{

        public OrderNotFoundException(Long id) {
            super("Could not find order " + id);
        }
    }

    @ControllerAdvice
    class OrderNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(OrderNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String orderNotFoundHandler(OrderNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
