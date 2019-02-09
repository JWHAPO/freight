package app.hapo.car.freight.domain.order;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * freight
 * Class: Order
 * Created by hapo on 2019-01-25.
 * Description:
 */
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name="ta2order")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long orderId;

    @Column(name = "description")
    private String description;

    @Column(name = "car_id")
    private Long carId;

    @Column(name="departure_address")
    private String departureAddress;

    @Column(name = "arrival_address")
    private String arrivalAddress;

    @Column(name = "distance")
    private Long distance;

    @Column(name = "hope_date")
    private LocalDate hopeDate;

    @Column(name = "hope_time")
    private LocalTime hopeTime;

    @Column(name = "hope_price")
    private Long hope_price;

    @Column(name = "is_mixed")
    private String isMixed;

    @Column(name = "remark")
    private String remark;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "cancel_remark")
    private String cancelRemark;

    @CreationTimestamp
    @Column(name = "created_time_at")
    private LocalDateTime createdTimeAt;

    @UpdateTimestamp
    @Column(name = "updated_time_at")
    private LocalDateTime updatedTimeAt;

    @Builder
    public Order(String description, Long carId, String departureAddress, String arrivalAddress, Long distance, LocalDate hopeDate, LocalTime hopeTime, Long hope_price, String isMixed, String remark, OrderStatus status, String cancelRemark) {
        this.description = description;
        this.carId = carId;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.distance = distance;
        this.hopeDate = hopeDate;
        this.hopeTime = hopeTime;
        this.hope_price = hope_price;
        this.isMixed = isMixed;
        this.remark = remark;
        this.status = status;
        this.cancelRemark = cancelRemark;
    }
}
