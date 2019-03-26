package app.hapo.car.freight.service.usercar;

import app.hapo.car.freight.domain.usercar.UserCar;
import app.hapo.car.freight.domain.usercar.UserCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<UserCar> findByUserId(Long userId, Pageable pageable) {
        PageRequest pageRequest =
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return userCarRepository.findByUserId(userId,pageRequest);
    }
}
