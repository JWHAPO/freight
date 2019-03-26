package app.hapo.car.freight.api.notice;/*
 * Created by hapo
 * Date : 19. 2. 9 오후 11:19
 * Description : NoticeController
 */

import app.hapo.car.freight.domain.notice.Notice;
import app.hapo.car.freight.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    NoticeResourceAssembler noticeResourceAssembler;

    @GetMapping(value = "/notices")
    public Resources<Resource<Notice>> findAll(Pageable pageable){
        List<Resource<Notice>> notices = noticeService.findAll(pageable).stream()
                .map(noticeResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(notices,
                linkTo(methodOn(NoticeController.class).findAll(pageable)).withSelfRel());
    }

    @GetMapping(value = "notices/{id}")
    public Resource<Notice> findById(@PathVariable Long id){
        return noticeResourceAssembler.toResource(
                noticeService.findById(id)
                .orElseThrow(()->new NoticeNotFoundException(id))
        );
    }


    class NoticeNotFoundException extends RuntimeException{

        public NoticeNotFoundException(Long id) {
            super("Could not find notice: " + id);
        }
    }

    @ControllerAdvice
    class NoticeNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(NoticeController.NoticeNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String noticeNotFoundHandler(NoticeController.NoticeNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
