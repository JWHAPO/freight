package app.hapo.car.freight.api.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:30
 * Description :  Car Controller-> 자동차에 대한 정보들을 다루는 컨트롤러
 */

import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.service.car.CarService;
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
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarResourceAssembler carResourceAssembler;

    @GetMapping(value = "/cars")
    public Resources<Resource<Car>> findAll(Pageable pageable){
        List<Resource<Car>> cars = carService.findAll(pageable).stream()
                .map(carResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(cars,
                linkTo(methodOn(CarController.class).findAll(pageable)).withSelfRel());
    }

    @GetMapping(value = "/cars/{id}")
    public Resource<Car> findById(@PathVariable Long id){
        return carResourceAssembler.toResource(
                carService.findById(id).orElseThrow(()->new CarNotFoundException(id))
        );
    }

    class CarNotFoundException extends RuntimeException{

        public CarNotFoundException(Long id) {
            super("Could not find Car " + id);
        }
    }

    @ControllerAdvice
    class CarNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(CarNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String carNotFoundHandler(CarNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
