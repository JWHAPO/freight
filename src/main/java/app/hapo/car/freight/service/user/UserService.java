package app.hapo.car.freight.service.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:04
 * Description :
 */

import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.domain.user.User;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByEmailAndPassword(String email, String password);
    Optional<User> findById(Long id);
    List<User> findAll(Sort sort);
    User createUser(User user);
    void deleteById(Long id);

}
