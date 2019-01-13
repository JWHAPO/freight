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

    @Column(name = "width")
    private Long width;

    @Column(name = "width_uom")
    private String widthUom;

    @Column(name = "length")
    private Long length;

    @Column(name = "length_uom")
    private String lengthUom;

    @Column(name = "loadable_height")
    private Long loadableHeight;

    @Column(name = "loadable_height_uom")
    private String loadableHeightUom;

    @Column(name = "loadable_weight")
    private Long loadableWeight;

    @Column(name = "loadable_weight_uom")
    private String loadableWeightUom;

}
