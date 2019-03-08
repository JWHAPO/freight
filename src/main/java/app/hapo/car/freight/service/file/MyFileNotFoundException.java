package app.hapo.car.freight.service.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * freight
 * Class: MyFileNotFoundException
 * Created by hapo on 2019-03-08.
 * Description:
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String message){
        super(message);
    }
    public MyFileNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
