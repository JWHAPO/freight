package app.hapo.car.freight.api.order;

import app.hapo.car.freight.domain.order.Order;
import app.hapo.car.freight.domain.order.OrderStatus;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * freight
 * Class: OrderResourceAssembler
 * Created by hapo on 2019-01-25.
 * Description: OrderResourceAssembler
 */
@Component
public class OrderResourceAssembler implements ResourceAssembler<Order, Resource<Order>> {
    @Override
    public Resource<Order> toResource(Order order) {
        Resource<Order> orderResource =  new Resource<>(order,
                linkTo(methodOn(OrderController.class).findById(order.getOrderId())).withSelfRel()
        );

        // Conditional links based on state of the order
        if(order.getStatus() == OrderStatus.IN_PROGRESS){
            orderResource.add(
                    linkTo(methodOn(OrderController.class)
                    .cancel(order.getOrderId(),"")).withRel("cancel")
            );
            orderResource.add(
                    linkTo(methodOn(OrderController.class)
                    .complete(order.getOrderId())).withRel("complete")
            );
        }


        return orderResource;
    }
}
