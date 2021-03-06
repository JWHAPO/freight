package app.hapo.car.freight.service.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:49
 * Description : BoardServiceImpl
 */

import app.hapo.car.freight.domain.board.Board;
import app.hapo.car.freight.domain.board.BoardRepository;
import app.hapo.car.freight.domain.board.BoardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Board> findByBoardType(BoardType boardType, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        return boardRepository.findByBoardType(boardType,pageRequest);
    }

    @Override
    public Page<Board> findByGroupId(Long groupId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        return boardRepository.findByGroupId(groupId,pageRequest);
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public Optional<Board> save(Board board) {
        return Optional.of(boardRepository.save(board));
    }

    @Override
    public void delete(Board board) {
        boardRepository.delete(board);
    }
}
