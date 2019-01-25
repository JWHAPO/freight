package app.hapo.car.freight.service.order;

import app.hapo.car.freight.domain.order.Order;

import java.util.List;
import java.util.Optional;

/**
 * freight
 * Class: OrderService
 * Created by hapo on 2019-01-25.
 * Description:
 */
public interface OrderService {
    List<Order> findAll();
    Optional<Order> findById(Long id);
    Optional<Order> save(Order order);
}
