package app.hapo.car.freight.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * freight
 * Class: OrderRepository
 * Created by hapo on 2019-01-25.
 * Description:
 */
public interface OrderRepository extends JpaRepository<Order,Long> {

    Long countByStatus(OrderStatus status);

    List<Order> findByStatus(OrderStatus status);
}
