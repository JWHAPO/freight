package app.hapo.car.freight.domain.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:44
 * Description : BoardRepository
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByBoardType(BoardType boardType, Pageable pageable);

    Page<Board> findByGroupId(Long groupId, Pageable pageable);
}
