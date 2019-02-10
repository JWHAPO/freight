package app.hapo.car.freight.service.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:49
 * Description : BoardServiceImpl
 */

import app.hapo.car.freight.domain.board.Board;
import app.hapo.car.freight.domain.board.BoardRepository;
import app.hapo.car.freight.domain.board.BoardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public List<Board> findByBoardType(BoardType boardType) {
        return boardRepository.findByBoardType(boardType);
    }

    @Override
    public List<Board> findByGroupId(Long groupId) {
        return boardRepository.findByGroupId(groupId);
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }
}
