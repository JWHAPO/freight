package app.hapo.car.freight.service.usercar;

import app.hapo.car.freight.domain.usercar.UserCar;
import app.hapo.car.freight.domain.usercar.UserCarRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * freight
 * Class: UserCarServiceImpl
 * Created by hapo on 2019-01-08.
 * Description:
 */

@Service
@Transactional
public class UserCarServiceImpl implements UserCarService{

    private UserCarRepository userCarRepository;

    public UserCarServiceImpl(UserCarRepository userCarRepository) {
        this.userCarRepository = userCarRepository;
    }

    @Override
    public List<UserCar> findByUserId(Long userId) {
        return userCarRepository.findByUserId(userId);
    }

    @Override
    public List<UserCar> findByCarId(Long carId) {
        return userCarRepository.findByCarId(carId);
    }
}