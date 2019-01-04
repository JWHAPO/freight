package app.hapo.car.freight.service.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:04
 * Description :
 */

import app.hapo.car.freight.domain.user.User;

public interface UserService {
    User findByEmailAndPassword(String email, String password);
}
