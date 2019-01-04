package app.hapo.car.freight.service.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:06
 * Description :
 */

import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.domain.car.CarRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
