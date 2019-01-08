package app.hapo.car.freight.service.usercar;

import app.hapo.car.freight.domain.usercar.UserCar;
import app.hapo.car.freight.domain.usercar.UserCarRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public UserCar createUserCar(UserCar userCar) {
        return userCarRepository.save(userCar);
    }
}
