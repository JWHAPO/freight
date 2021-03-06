package app.hapo.car.freight.api.order.response;/*
 * Created by hapo
 * Date : 19. 2. 6 오후 10:09
 * Description : OrderResponseController 주문에 대한 응답 api
 */

import app.hapo.car.freight.domain.order.response.OrderResponse;
import app.hapo.car.freight.service.order.response.OrderResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class OrderResponseController {

    @Autowired
    OrderResponseService orderResponseService;

    @Autowired
    OrderResponseResourceAssembler orderResponseResourceAssembler;

    @GetMapping(value = "/order-responses")
    public Resources<Resource<OrderResponse>> findAll(Pageable pageable){
        List<Resource<OrderResponse>> orderResponses = orderResponseService.findAll(pageable).stream()
                .map(orderResponseResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orderResponses,
                linkTo(methodOn(OrderResponseController.class).findAll(pageable)).withSelfRel());
    }

    @GetMapping(value = "/order-responses/{id}")
    public Resource<OrderResponse> findById(@PathVariable Long id){
        return orderResponseResourceAssembler.toResource(
                orderResponseService.findById(id)
                .orElseThrow(()->new OrderResponseNotFoundException(id))
        );
    }

    @GetMapping(value = "/order-responses/order/{id}")
    public Resources<Resource<OrderResponse>> findByOrderId(@PathVariable Long id,Pageable pageable){
        List<Resource<OrderResponse>> orderResponses = orderResponseService.findByOrderId(id,pageable).stream()
                .map(orderResponseResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orderResponses,
                linkTo(methodOn(OrderResponseController.class).findByOrderId(id,pageable)).withSelfRel());
    }

    @GetMapping(value = "/order-responses/user/{id}")
    public Resources<Resource<OrderResponse>> findByUserId(@PathVariable Long id,Pageable pageable){
        List<Resource<OrderResponse>> orderResponses = orderResponseService.findByUserId(id,pageable).stream()
                .map(orderResponseResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orderResponses,
                linkTo(methodOn(OrderResponseController.class).findByUserId(id,pageable)).withSelfRel());
    }

    @GetMapping(value = "/order-response/order/{id}/count")
    public Long countByOrderId(@PathVariable Long id){
        return orderResponseService.countByOrderId(id);
    }

    @PostMapping(value = "/order-response")
    public ResponseEntity<Resource<OrderResponse>> newOrderResponse(@RequestBody OrderResponse orderResponse){
        OrderResponse newOrderResponse = orderResponseService.save(orderResponse).get();

        return ResponseEntity
                .created(linkTo(methodOn(OrderResponseController.class).findById(newOrderResponse.getOrderResponseId())).toUri())
                .body(orderResponseResourceAssembler.toResource(newOrderResponse));

    }

    class OrderResponseNotFoundException extends RuntimeException{

        public OrderResponseNotFoundException(Long id) {
            super("Could not find order response: " + id);
        }
    }

    @ControllerAdvice
    class OrderResponseNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(OrderResponseNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String orderNotFoundHandler(OrderResponseNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
