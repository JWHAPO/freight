package app.hapo.car.freight.service.usercar;

import app.hapo.car.freight.domain.usercar.UserCar;
import app.hapo.car.freight.domain.usercar.UserCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private UserCarRepository userCarRepository;

    @Override
    public UserCar createUserCar(UserCar userCar) {
        return userCarRepository.save(userCar);
    }

    @Override
    public List<UserCar> findByUserId(Long userId) {
        return userCarRepository.findByUserId(userId);
    }
}
