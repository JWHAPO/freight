package app.hapo.car.freight.domain.board

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import spock.lang.Specification

/*
 * Created by hapo
 * Date : 19. 3. 26 오후 11:28
 * Description : 
 */

@SpringBootTest
class BoardRepositoryTest extends Specification {

    @Autowired
    BoardRepository boardRepository

    //@Before
    def setup(){

        Board board1 = new Board(1L, 0L, 1L, app.hapo.car.freight.domain.board.BoardType.BBS, "TITLE 1", "CONTENTS 1", 4L,"N")
        Board board2 = new Board(1L, 1L, 1L, app.hapo.car.freight.domain.board.BoardType.BBS, "TITLE 2", "CONTENTS 2", 5L,"Y")
        Board board3 = new Board(2L, 0L, 1L, app.hapo.car.freight.domain.board.BoardType.BBS, "TITLE 3", "CONTENTS 3", 112L,"N")

        boardRepository.save(board1)
        boardRepository.save(board2)
        boardRepository.save(board3)

    }

    def cleanup(){
        boardRepository.deleteAll()
    }

    def "findAll"() {
        when:
            List<Board> boards = boardRepository.findAll()
        then:
            boards.size() == 3
            boards.get(0).boardType == BoardType.BBS
            boards.get(0).hits == 4L
    }

    def "findByGroupId"(){
        when:
        Page<Board> boards = boardRepository.findByBoardType(BoardType.BBS,new Pageable() {
            @Override
            int getPageNumber() {
                return 1
            }

            @Override
            int getPageSize() {
                return 20
            }

            @Override
            long getOffset() {
                return 0
            }

            @Override
            Sort getSort() {
                return Sort.by("boardId").descending()
            }

            @Override
            Pageable next() {
                return null
            }

            @Override
            Pageable previousOrFirst() {
                return null
            }

            @Override
            Pageable first() {
                return null
            }

            @Override
            boolean hasPrevious() {
                return false
            }
        })

        then:
        boards.size == 20
        boards.getContent().size() == 3
        boards.getContent().get(0).getHits() == 112L
    }

}
