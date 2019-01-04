package app.hapo.car.freight.domain.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 10:43
 * Description : Car DTO
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
}
