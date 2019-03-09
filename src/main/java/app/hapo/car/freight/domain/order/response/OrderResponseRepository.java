package app.hapo.car.freight.domain.order.response;/*
 * Created by hapo
 * Date : 19. 2. 6 오후 10:08
 * Description : 주문에 대한 응답 repository
 */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderResponseRepository extends JpaRepository<OrderResponse,Long> {

    List<OrderResponse> findByOrderId(Long orderId);

    Long countByOrderId(Long orderId);

}
