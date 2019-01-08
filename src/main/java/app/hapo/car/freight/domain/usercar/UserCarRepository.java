package app.hapo.car.freight.domain.usercar;/*
 * Created by hapo
 * Date : 19. 1. 8 오후 11:02
 * Description :
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCarRepository extends JpaRepository<UserCar, Long> {
    List<UserCar> findByUserId(@Param("userId") Long userId);
    List<UserCar> findByCarId(@Param("carId") Long carId);

}
