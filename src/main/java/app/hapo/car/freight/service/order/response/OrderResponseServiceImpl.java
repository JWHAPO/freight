package app.hapo.car.freight.service.order.response;/*
 * Created by hapo
 * Date : 19. 2. 6 오후 10:13
 * Description : OrderResponseServiceImpl
 */

import app.hapo.car.freight.domain.order.response.OrderResponse;
import app.hapo.car.freight.domain.order.response.OrderResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<OrderResponse> findByOrderId(Long orderId, Pageable pageable) {
        PageRequest pageRequest =
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return orderResponseRepository.findByOrderId(orderId, pageRequest);
    }

    @Override
    public Page<OrderResponse> findByUserId(Long userId, Pageable pageable) {
        PageRequest pageRequest =
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return orderResponseRepository.findByUserId(userId, pageRequest);
    }

    @Override
    public Page<OrderResponse> findAll(Pageable pageable) {
        PageRequest pageRequest =
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return orderResponseRepository.findAll(pageRequest);
    }

    @Override
    public Optional<OrderResponse> findById(Long orderResponseId) {
        return orderResponseRepository.findById(orderResponseId);
    }

    @Override
    public Long countByOrderId(Long orderId) {
        return orderResponseRepository.countByOrderId(orderId);
    }

    @Override
    public Optional<OrderResponse> save(OrderResponse orderResponse) {
        return Optional.of(orderResponseRepository.save(orderResponse));
    }
}
