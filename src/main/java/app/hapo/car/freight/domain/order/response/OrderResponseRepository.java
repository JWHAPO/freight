package app.hapo.car.freight.domain.order.response;/*
 * Created by hapo
 * Date : 19. 2. 6 오후 10:08
 * Description : 주문에 대한 응답 repository
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderResponseRepository extends JpaRepository<OrderResponse,Long> {

    Page<OrderResponse> findByOrderId(Long orderId, Pageable pageable);

    Page<OrderResponse> findByUserId(Long userId, Pageable pageable);

    Optional<OrderResponse> findByOrderIdAndIsSelected(Long orderId, String isSelected);

    Long countByOrderId(Long orderId);

}
