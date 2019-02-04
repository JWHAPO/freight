package app.hapo.car.freight.domain.order.response;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * freight
 * Class: OrderResponse
 * Created by hapo on 2019-02-05.
 * Description: Order에 대한 응답
 */

@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name="ta2order_response")
public class OrderResponse {
    @Id
    @Column(name = "order_response_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long orderResponseId;

    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "response")
    private Long response;
    @Column(name = "is_selection")
    private Long isSelection;
    @CreationTimestamp
    @Column(name = "created_time_at")
    private LocalDateTime createdTimeAt;
    @UpdateTimestamp
    @Column(name = "updated_time_at")
    private LocalDateTime updatedTimeAt;

    @Builder
    public OrderResponse(Long orderId, Long userId, Long response, Long isSelection) {
        this.orderId = orderId;
        this.userId = userId;
        this.response = response;
        this.isSelection = isSelection;
    }
}
