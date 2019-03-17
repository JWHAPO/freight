package app.hapo.car.freight.service.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:47
 * Description : BoardService
 */

import app.hapo.car.freight.domain.board.Board;
import app.hapo.car.freight.domain.board.BoardType;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    List<Board> findByBoardType(BoardType boardType);

    List<Board> findByGroupId(Long groupId);

    Optional<Board> findById(Long id);

    Optional<Board> save(Board board);

    void delete(Board board);

}
