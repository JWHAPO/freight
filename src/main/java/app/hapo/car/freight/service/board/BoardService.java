package app.hapo.car.freight.service.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:47
 * Description : BoardService
 */

import app.hapo.car.freight.domain.board.Board;
import app.hapo.car.freight.domain.board.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    Page<Board> findByBoardType(BoardType boardType, Pageable pageable);

    Page<Board> findByGroupId(Long groupId, Pageable pageable);

    Optional<Board> findById(Long id);

    Optional<Board> save(Board board);

    void delete(Board board);

}
