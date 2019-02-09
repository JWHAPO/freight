package app.hapo.car.freight.api.notice;/*
 * Created by hapo
 * Date : 19. 2. 9 오후 11:19
 * Description : NoticeController
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class NoticeController {





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
        String orderNotFoundHandler(NoticeController.NoticeNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
