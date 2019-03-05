package app.hapo.car.freight.service.order.response;/*
 * Created by hapo
 * Date : 19. 2. 6 오후 10:13
 * Description : OrderResponseServiceImpl
 */

import app.hapo.car.freight.domain.order.response.OrderResponse;
import app.hapo.car.freight.domain.order.response.OrderResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderResponseServiceImpl implements OrderResponseService {

    @Autowired
    OrderResponseRepository orderResponseRepository;

    @Override
    public Optional<OrderResponse> findByOrderId(Long orderId) {
        return orderResponseRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderResponseRepository.findAll();
    }

    @Override
    public Optional<OrderResponse> findById(Long orderResponseId) {
        return orderResponseRepository.findById(orderResponseId);
    }

    @Override
    public Long countByOrderId(Long orderId) {
        return orderResponseRepository.countByOrderId(orderId);
    }
}
