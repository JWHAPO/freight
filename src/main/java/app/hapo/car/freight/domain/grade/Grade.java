package app.hapo.car.freight.domain.grade;/*
 * Created by hapo
 * Date : 19. 3. 4 오후 10:40
 * Description : Grade
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
@Table(name="ta2grade")
public class Grade {
    @Id
    @Column(name="grade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @Column(name = "grade_no")
    private Long gradeNo;

    @Column(name = "from_exp")
    private Long fromExp;

    @Column(name = "to_exp")
    private Long toExp;

    @CreationTimestamp
    @Column(name = "created_time_at")
    private LocalDateTime createdTimeAt;

    @UpdateTimestamp
    @Column(name = "updated_time_at")
    private LocalDateTime updatedTimeAt;

    @Builder
    public Grade(Long gradeNo, Long fromExp, Long toExp) {
        this.gradeNo = gradeNo;
        this.fromExp = fromExp;
        this.toExp = toExp;
    }
}
