package app.hapo.car.freight.domain.user.Role;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * freight
 * Class: Role
 * Created by hapo on 2019-01-31.
 * Description: Role Model
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ta2role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long roleId;


    @Column(name = "name")
    private RoleName name;

    @CreationTimestamp
    @Column(name = "created_time_at")
    private LocalDateTime createdTimeAt;

    @UpdateTimestamp
    @Column(name = "updated_time_at")
    private LocalDateTime updatedTimeAt;

    @Builder
    public Role(RoleName name){
        this.name = name;
    }
}
