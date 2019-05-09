package app.hapo.car.freight.service.order;

import app.hapo.car.freight.domain.order.Order;
import app.hapo.car.freight.domain.order.OrderRepository;
import app.hapo.car.freight.domain.order.OrderStatus;
import app.hapo.car.freight.domain.order.response.OrderResponse;
import app.hapo.car.freight.domain.order.response.OrderResponseRepository;
import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderResponseRepository orderResponseRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<Order> findAll(Pageable pageable) {

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        return orderRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> save(Order order) {
        return Optional.of(orderRepository.save(order));
    }

    @Override
    public int countByStatus(OrderStatus status) {
        return orderRepository.countByStatus(status);
    }

    @Override
    public Page<Order> findByStatus(OrderStatus status, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        return orderRepository.findByStatus(status, pageRequest);
    }

    @Override
    public Optional<Order> complete(Order order) {

        Optional<OrderResponse> orderResponse = orderResponseRepository.findByOrderIdAndIsSelected(order.getOrderId(), "Y");
        Optional<User> user = userRepository.findById(orderResponse.get().getUserId());

        //add point
        Long resultUserPoint = user.get().getExperienceValue() + (orderResponse.get().getResultPoint() * 5 );
        user.get().setExperienceValue(resultUserPoint);
        userRepository.save(user.get());

        return Optional.of(orderRepository.save(order));
    }
}
