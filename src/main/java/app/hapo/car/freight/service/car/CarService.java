package app.hapo.car.freight.service.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:04
 * Description :
 */

import app.hapo.car.freight.domain.car.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CarService {

    Page<Car> findAll(Pageable pageable);

    Optional<Car> findById(Long id);

}
