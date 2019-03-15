package app.hapo.car.freight.service.order.response;/*
 * Created by hapo
 * Date : 19. 2. 6 오후 10:10
 * Description :
 */

import app.hapo.car.freight.domain.order.response.OrderResponse;

import java.util.List;
import java.util.Optional;

public interface OrderResponseService {

    /**
     * 주문ID로 답변데이터 검색
     * * @param orderId
     * @return
     */
    List<OrderResponse> findByOrderId(Long orderId);
    List<OrderResponse> findByUserId(Long userId);

    /**
     * 전체 응답리스트
     * @return
     */
    List<OrderResponse> findAll();

    /**
     * id로 응답데이터 검색
     * @param orderResponseId
     * @return
     */
    Optional<OrderResponse> findById(Long orderResponseId);

    /**
     * order에 붙은 response 수
     */
    Long countByOrderId(Long orderId);

    Optional<OrderResponse> save(OrderResponse orderResponse);

}
