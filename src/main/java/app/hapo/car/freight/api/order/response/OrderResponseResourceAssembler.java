package app.hapo.car.freight.api.order.response;/*
 * Created by hapo
 * Date : 19. 2. 6 오후 10:19
 * Description : OrderResponseResourceAssembler
 */

import app.hapo.car.freight.domain.order.response.OrderResponse;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class OrderResponseResourceAssembler implements ResourceAssembler<OrderResponse, Resource<OrderResponse>> {

    @Override
    public Resource<OrderResponse> toResource(OrderResponse orderResponse) {
        return new Resource<>(orderResponse,
                linkTo(methodOn(OrderResponseController.class).findById(orderResponse.getOrderResponseId())).withSelfRel(),
                linkTo(methodOn(OrderResponseController.class).findAll()).withRel("responses"));
    }
}
