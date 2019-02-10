package app.hapo.car.freight.domain.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:44
 * Description : BoardRepository
 */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByBoardType(BoardType boardType);

    List<Board> findByGroupId(Long groupId);
}
