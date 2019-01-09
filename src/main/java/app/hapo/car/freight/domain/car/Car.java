package app.hapo.car.freight.domain.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 10:43
 * Description : Car DTO
 */

import app.hapo.car.freight.domain.usercar.UserCar;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name="ta2car")
public class Car {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType. AUTO)
    private Long carId;

    @Column(name = "car_no")
    private Long carNo;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "weight_uom")
    private String weightUom;

    @Column(name = "maker")
    private String maker;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<UserCar> userCars;

}
