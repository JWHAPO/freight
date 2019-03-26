package app.hapo.car.freight.api.order.file;/*
 * Created by hapo
 * Date : 19. 3. 10 오전 1:11
 * Description :
 */

import app.hapo.car.freight.domain.order.file.OrderFile;
import app.hapo.car.freight.service.order.file.OrderFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class OrderFileController {

    @Autowired
    OrderFileService orderFileService;

    @Autowired
    OrderFileResourceAssembler orderFileResourceAssembler;

    @GetMapping(value = "/order-files")
    public Resources<Resource<OrderFile>> findAll(Pageable pageable){
        List<Resource<OrderFile>> orderFiles = orderFileService.findAll(pageable).stream()
                .map(orderFileResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orderFiles,
                linkTo(methodOn(OrderFileController.class).findAll(pageable)).withSelfRel());
    }

    @GetMapping(value = "/order-files/{id}")
    public Resource<OrderFile> findById(@PathVariable Long id){
        return orderFileResourceAssembler.toResource(
                orderFileService.findById(id)
                .orElseThrow(()->new OrderFileNotFoundException(id))
        );
    }

    @GetMapping(value = "/order-files/order/{id}")
    public Resources<Resource<OrderFile>> findByOrderId(@PathVariable Long id, Pageable pageable){
        List<Resource<OrderFile>> orderFiles = orderFileService.findByOrderId(id,pageable).stream()
                .map(orderFileResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orderFiles,
                linkTo(methodOn(OrderFileController.class).findByOrderId(id,pageable)).withSelfRel());
    }

    class OrderFileNotFoundException extends RuntimeException{

        public OrderFileNotFoundException(Long id) {
            super("Could not find order file: " + id);
        }
    }

    @ControllerAdvice
    class OrderFileNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(OrderFileNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String orderNotFoundHandler(OrderFileNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
