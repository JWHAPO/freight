package app.hapo.car.freight.domain.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:02
 * Description :
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from ta2user a where a.email=:email and a.password = :password", nativeQuery = true)
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
