package app.hapo.car.freight.domain.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:35
 * Description : Board DTO
 */

import app.hapo.car.freight.domain.common.AuditModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="ta2board")
public class Board extends AuditModel {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "parent_board_id")
    private Long parentBoardId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "board_type")
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "hits")
    private Long hits;

    @Column(name = "is_secret")
    private String isSecret;

    @Builder
    public Board(Long groupId, Long parentBoardId, Long userId, BoardType boardType, String title, String contents, Long hits, String isSecret) {
        this.groupId = groupId;
        this.parentBoardId = parentBoardId;
        this.userId = userId;
        this.boardType = boardType;
        this.title = title;
        this.contents = contents;
        this.hits = hits;
        this.isSecret = isSecret;
    }
}
