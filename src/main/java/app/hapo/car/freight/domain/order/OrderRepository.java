package app.hapo.car.freight.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * freight
 * Class: OrderRepository
 * Created by hapo on 2019-01-25.
 * Description:
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
}
