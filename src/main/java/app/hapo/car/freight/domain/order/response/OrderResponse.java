package app.hapo.car.freight.domain.order.response;

import app.hapo.car.freight.domain.order.Order;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    @Column(name = "pickup_date")
    private LocalDate pickupDate;
    @Column(name = "pickup_time")
    private LocalTime pickupTime;
    @Column(name = "suggested_price")
    private Long suggestedPrice;
    @Column(name = "current_avg_price")
    private Long currentAvgPrice;
    @Column(name = "seller_message")
    private String sellerMessage;
    @Column(name = "buyer_message")
    private String buyerMessage;
    @Column(name = "result_point")
    private Long resultPoint;
    @Column(name = "is_selected")
    private String isSelected;
    @ManyToOne
    @JoinColumn(name = "order_id",  insertable = false, updatable = false )
    private Order order;
    @CreationTimestamp
    @Column(name = "created_time_at")
    private LocalDateTime createdTimeAt;
    @UpdateTimestamp
    @Column(name = "updated_time_at")
    private LocalDateTime updatedTimeAt;

    @Builder
    public OrderResponse(Long orderId, Long userId, LocalDate pickupDate, LocalTime pickupTime, Long suggestedPrice, Long currentAvgPrice, String sellerMessage, String buyerMessage, Long resultPoint, String isSelected, Order order) {
        this.orderId = orderId;
        this.userId = userId;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.suggestedPrice = suggestedPrice;
        this.currentAvgPrice = currentAvgPrice;
        this.sellerMessage = sellerMessage;
        this.buyerMessage = buyerMessage;
        this.resultPoint = resultPoint;
        this.isSelected = isSelected;
        this.order = order;
    }
}
