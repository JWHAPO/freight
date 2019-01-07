package app.hapo.car.freight.api.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:30
 * Description :
 */

import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.service.car.CarService;
import app.hapo.car.freight.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public List<Car> findAll(){
        List<Car> cars = carService.findAll();
        return cars;
    }
}
