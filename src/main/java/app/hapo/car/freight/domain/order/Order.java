package app.hapo.car.freight.domain.order;

import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.domain.common.AuditModel;
import app.hapo.car.freight.domain.order.response.OrderResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
public class Order extends AuditModel {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "description")
    private String description;

    @Column(name = "car_id")
    private Long carId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="car_id")
    private Car car;

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
    private Long hopePrice;

    @Column(name = "is_mixed")
    private String isMixed;

    @Column(name = "remark")
    private String remark;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "cancel_remark")
    private String cancelRemark;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName="order_id",  insertable = false, updatable = false)
    private List<OrderResponse> orderResponses;

    @Builder
    public Order(String description, Long carId,Car car, String departureAddress, String arrivalAddress, Long distance, LocalDate hopeDate, LocalTime hopeTime, Long hopePrice, String isMixed, String remark, OrderStatus status, String cancelRemark, List<OrderResponse> orderResponses) {
        this.description = description;
        this.carId = carId;
        this.car = car;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.distance = distance;
        this.hopeDate = hopeDate;
        this.hopeTime = hopeTime;
        this.hopePrice = hopePrice;
        this.isMixed = isMixed;
        this.remark = remark;
        this.status = status;
        this.cancelRemark = cancelRemark;
        this.orderResponses = orderResponses;
    }
}
