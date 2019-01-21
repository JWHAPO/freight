package app.hapo.car.freight.domain.auth.email;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * freight
 * Class: EmailAuth
 * Created by hapo on 2019-01-18.
 * Description: Email 인증
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ta2email_auth")
public class EmailAuth {
    @Id
    @Column(name = "email_auth_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long emailAuthId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "auth_key")
    private String authKey;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Column(name = "is_auth")
    private String isAuth;

    @Builder
    public EmailAuth(Long userId, String email, String authKey, LocalDateTime regDate, LocalDateTime expiredDate, String isAuth) {
        this.userId = userId;
        this.email = email;
        this.authKey = authKey;
        this.regDate = regDate;
        this.expiredDate = expiredDate;
        this.isAuth = isAuth;
    }
}
