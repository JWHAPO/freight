package app.hapo.car.freight.domain.user;

import app.hapo.car.freight.domain.common.AuditModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * freight
 * Class: UserRole
 * Created by hapo on 2019-02-01.
 * Description:
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ta2user_role")
public class UserRole extends AuditModel {
    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long userRoleId;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",  updatable=false)
    protected Role role;

    @Builder
    public UserRole(Long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }
}
