package app.hapo.car.freight.api.car;/*
 * Created by hapo
 * Date : 19. 3. 18 오후 9:30
 * Description :
 */

import app.hapo.car.freight.domain.car.Car;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CarResourceAssembler implements ResourceAssembler<Car, Resource<Car>> {

    @Override
    public Resource<Car> toResource(Car car) {

        Resource<Car> carResource = new Resource<>(car,
                linkTo(methodOn(CarController.class).findById(car.getCarId())).withSelfRel(),
                linkTo(methodOn(CarController.class).findAll()).withRel("cars"));
        return carResource;
    }
}
