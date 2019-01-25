package app.hapo.car.freight.domain.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * freight
 * Class: Order
 * Created by hapo on 2019-01-25.
 * Description:
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ta2order")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long orderId;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    @Column(name = "created_time_at")
    private LocalDateTime createdTimeAt;

    @UpdateTimestamp
    @Column(name = "updated_time_at")
    private LocalDateTime updatedTimeAt;

    public Order(String description, OrderStatus status) {
        this.description = description;
        this.status = status;
    }
}
