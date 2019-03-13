package app.hapo.car.freight.domain.notice;/*
 * Created by hapo
 * Date : 19. 2. 9 오후 11:12
 * Description :공지사항
 */

import app.hapo.car.freight.domain.common.AuditModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ta2notice")
public class Notice extends AuditModel {
    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "hits")
    private Long hits;

    @Builder
    public Notice(Long userId, String title, String contents, Long hits) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.hits = hits;
    }
}
