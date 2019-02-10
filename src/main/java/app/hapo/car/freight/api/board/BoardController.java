package app.hapo.car.freight.api.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:54
 * Description : BoardController
 */

import app.hapo.car.freight.domain.board.Board;
import app.hapo.car.freight.domain.board.BoardType;
import app.hapo.car.freight.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
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
    public Resources<Resource<Board>> findQuestions(){
        List<Resource<Board>> questions = boardService.findByBoardType(BoardType.QUESTION).stream()
                .map(boardResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(questions,
                linkTo(methodOn(BoardController.class).findQuestions()).withSelfRel());
    }

    @GetMapping(value = "/boards/group/{id}")
    public Resources<Resource<Board>> findByGroupId(@PathVariable Long groupId){
        List<Resource<Board>> boardsByGroupId = boardService.findByGroupId(groupId).stream()
                .map(boardResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(boardsByGroupId,
                linkTo(methodOn(BoardController.class).findByGroupId(groupId)).withSelfRel());
    }

    @GetMapping(value = "/boards/{id}")
    public Resource<Board> findById(@PathVariable Long id){
        return boardResourceAssembler.toResource(
                boardService.findById(id)
                .orElseThrow(()->new BoardNotFoundException(id))
        );
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
