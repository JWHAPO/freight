package app.hapo.car.freight.domain.usercar;/*
 * Created by hapo
 * Date : 19. 1. 8 오후 10:43
 * Description : User의 Car목록을 들고 있는 테이블
 */

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name="ta2user_car")
public class UserCar {
    @Id
    @Column(name = "user_car_id")
    @GeneratedValue(strategy = GenerationType. AUTO)
    private Long userCarId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "car_id")
    private String carId;


}
