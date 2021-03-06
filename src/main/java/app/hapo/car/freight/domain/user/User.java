package app.hapo.car.freight.domain.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 10:43
 * Description :
 */

import app.hapo.car.freight.domain.common.AuditModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ta2user")
public class User extends AuditModel {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long userId;

    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "experience_value")
    private Long experienceValue;

    @Column(name = "level_id")
    private Long levelId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName="user_id",  insertable = false, updatable = false)
    private List<UserRole> roles;

    @Builder
    public User(Long userNo, String email, String password, String name, String phone, String imagePath, Long experienceValue, Long levelId, List<UserRole> roles) {
        this.userNo = userNo;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.imagePath = imagePath;
        this.experienceValue = experienceValue;
        this.levelId = levelId;
        this.roles = roles;
    }
}
