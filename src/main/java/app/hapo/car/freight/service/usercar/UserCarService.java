package app.hapo.car.freight.service.usercar;
/*
 * Created by hapo
 * Date : 19. 1. 9 오후 11:04
 * Description : User 의 Car Service
 */

import app.hapo.car.freight.domain.usercar.UserCar;

public interface UserCarService {
    UserCar createUserCar(UserCar userCar);
}
