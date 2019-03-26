package app.hapo.car.freight.service.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:04
 * Description :
 */

import app.hapo.car.freight.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailHavingAuth(String email);
    Page<User> findAll(Pageable pageable);
    Optional<User> save(User user);
    void delete(User user);

}
