package app.hapo.car.freight.domain.usercar;/*
 * Created by hapo
 * Date : 19. 1. 8 오후 11:02
 * Description :
 */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCarRepository extends JpaRepository<UserCar, Long> {
    List<UserCar> findByUserId(Long userId);
}
