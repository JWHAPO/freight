package app.hapo.car.freight.domain.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:02
 * Description :
 */

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

}
