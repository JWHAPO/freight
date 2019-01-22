package app.hapo.car.freight.service.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:04
 * Description :
 */

import app.hapo.car.freight.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByEmailAndPassword(String email, String password);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    User createUser(User user);
    void deleteById(Long id);

}
