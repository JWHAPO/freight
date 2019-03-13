package app.hapo.car.freight.domain.usercar;/*
 * Created by hapo
 * Date : 19. 1. 8 오후 10:43
 * Description : User의 Car목록을 들고 있는 테이블
 */

import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.domain.common.AuditModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ta2user_car")
public class UserCar extends AuditModel {
    @Id
    @Column(name = "user_car_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long userCarId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "car_id")
    private Long carId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id", insertable = false, updatable = false)
    private Car car;

    @Builder
    public UserCar(Long userId, Long carId, Car car) {
        this.userId = userId;
        this.carId = carId;
        this.car = car;
    }
}
