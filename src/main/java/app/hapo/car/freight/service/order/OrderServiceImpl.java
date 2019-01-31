package app.hapo.car.freight.service.order;

import app.hapo.car.freight.domain.order.Order;
import app.hapo.car.freight.domain.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * freight
 * Class: OrderServiceImpl
 * Created by hapo on 2019-01-25.
 * Description: Order service implementation
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> save(Order order) {
        return Optional.of(orderRepository.save(order));
    }
}
