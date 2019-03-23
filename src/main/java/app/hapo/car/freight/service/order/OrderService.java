package app.hapo.car.freight.service.order;

import app.hapo.car.freight.domain.order.Order;
import app.hapo.car.freight.domain.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * freight
 * Class: OrderService
 * Created by hapo on 2019-01-25.
 * Description:
 */
public interface OrderService {
    Page<Order> findAll(Pageable pageable);
    Optional<Order> findById(Long id);
    Optional<Order> save(Order order);
    Long countByStatus(OrderStatus status);
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);
}
