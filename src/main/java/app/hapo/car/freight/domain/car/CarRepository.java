package app.hapo.car.freight.domain.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:02
 * Description :
 */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByUserIdIn(List<Long> userIds);

}
