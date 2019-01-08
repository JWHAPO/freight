package app.hapo.car.freight.domain.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 10:43
 * Description : Car DTO
 */

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name="ta2car")
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "no")
    private Long no;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "weight_uom")
    private String weightUom;

    @Column(name = "maker")
    private String maker;

    @Column(name = "type")
    private String type;
}
