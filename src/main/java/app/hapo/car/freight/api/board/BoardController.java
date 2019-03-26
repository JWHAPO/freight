package app.hapo.car.freight.api.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:54
 * Description : BoardController
 */

import app.hapo.car.freight.domain.board.Board;
import app.hapo.car.freight.domain.board.BoardType;
import app.hapo.car.freight.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardResourceAssembler boardResourceAssembler;

    @GetMapping(value = "/boards/questions")
    public Resources<Resource<Board>> findQuestions(Pageable pageable){
        List<Resource<Board>> questions = boardService.findByBoardType(BoardType.QUESTION,pageable).stream()
                .map(boardResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(questions,
                linkTo(methodOn(BoardController.class).findQuestions(pageable)).withSelfRel());
    }

    @GetMapping(value = "/boards/group/{id}")
    public Resources<Resource<Board>> findByGroupId(@PathVariable Long groupId,Pageable pageable){
        List<Resource<Board>> boardsByGroupId = boardService.findByGroupId(groupId,pageable).stream()
                .map(boardResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(boardsByGroupId,
                linkTo(methodOn(BoardController.class).findByGroupId(groupId,pageable)).withSelfRel());
    }

    @GetMapping(value = "/boards/{id}")
    public Resource<Board> findById(@PathVariable Long id){
        return boardResourceAssembler.toResource(
                boardService.findById(id)
                .orElseThrow(()->new BoardNotFoundException(id))
        );
    }

    @PostMapping(value = "/boards")
    public ResponseEntity<Resource<Board>> newBoard(@RequestBody Board board){
        Board newBoard = boardService.save(board).get();

        return ResponseEntity
                .created(linkTo(methodOn(BoardController.class).findById(newBoard.getBoardId())).toUri())
                .body(boardResourceAssembler.toResource(newBoard));
    }

    @DeleteMapping(value = "/boards/{id}")
    public ResponseEntity<ResourceSupport> delete(@PathVariable Long id){
        Board board = boardService.findById(id).orElseThrow(() -> new BoardNotFoundException(id));
        boardService.delete(board);
        return ResponseEntity.noContent().build();
    }

    class BoardNotFoundException extends RuntimeException{

        public BoardNotFoundException(Long id) {
            super("Could not find board " + id);
        }
    }

    @ControllerAdvice
    class BoardNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(BoardNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String boardNotFoundHandler(BoardNotFoundException ex) {
            return ex.getMessage();
        }
    }



}
