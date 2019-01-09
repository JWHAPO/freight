package app.hapo.car.freight.service.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:04
 * Description :
 */

import app.hapo.car.freight.domain.car.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();
}
