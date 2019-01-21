package app.hapo.car.freight.domain.car;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 10:43
 * Description : Car DTO
 */

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ta2car")
public class Car {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
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

    @CreationTimestamp
    @Column(name = "created_time_at")
    private LocalDateTime createdTimeAt;

    @UpdateTimestamp
    @Column(name = "updated_time_at")
    private LocalDateTime updatedTimeAt;

    @Builder
    public Car(Long carNo, String description, Long width, String widthUom, Long length, String lengthUom, Long loadableHeight, String loadableHeightUom, Long loadableWeight, String loadableWeightUom) {
        this.carNo = carNo;
        this.description = description;
        this.width = width;
        this.widthUom = widthUom;
        this.length = length;
        this.lengthUom = lengthUom;
        this.loadableHeight = loadableHeight;
        this.loadableHeightUom = loadableHeightUom;
        this.loadableWeight = loadableWeight;
        this.loadableWeightUom = loadableWeightUom;
    }
}
