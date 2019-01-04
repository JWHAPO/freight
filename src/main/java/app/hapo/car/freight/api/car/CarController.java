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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<Car> findAll(){
        List<Car> cars = carService.findAll();
        return cars;
    }
}
