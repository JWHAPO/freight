package app.hapo.car.freight.service.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:04
 * Description :
 */

import app.hapo.car.freight.domain.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll();

    Optional<Car> findById(Long id);

}
