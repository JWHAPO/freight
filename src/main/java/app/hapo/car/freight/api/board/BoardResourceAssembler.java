package app.hapo.car.freight.api.board;/*
 * Created by hapo
 * Date : 19. 2. 10 오후 4:56
 * Description : BoardResourceAssembler
 */

import app.hapo.car.freight.domain.board.Board;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BoardResourceAssembler implements ResourceAssembler<Board, Resource<Board>> {
    @Override
    public Resource<Board> toResource(Board board) {

        Resource<Board> boardResource = new Resource<>(board,
                linkTo(methodOn(BoardController.class).findById(board.getBoardId())).withSelfRel());

        return boardResource;
    }
}
