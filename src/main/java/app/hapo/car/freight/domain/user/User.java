package app.hapo.car.freight.domain.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 10:43
 * Description :
 */

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name="ta2user")
public class User {
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
}
