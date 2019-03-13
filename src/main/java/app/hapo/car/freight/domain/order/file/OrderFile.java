package app.hapo.car.freight.domain.order.file;/*
 * Created by hapo
 * Date : 19. 3. 9 오후 7:00
 * Description :
 */

import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.domain.common.AuditModel;
import app.hapo.car.freight.domain.file.File;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ta2order_file")
public class OrderFile extends AuditModel {

    @Id
    @Column(name = "order_file_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long orderFileId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "file_id")
    private Long fileId;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "file_id", insertable = false, updatable = false)
    private File file;

    @Builder
    public OrderFile(Long orderId, Long fileId, File file) {
        this.orderId = orderId;
        this.fileId = fileId;
        this.file = file;
    }
}
