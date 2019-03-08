package app.hapo.car.freight.service.file;

/**
 * freight
 * Class: FileStorageException
 * Created by hapo on 2019-03-08.
 * Description:
 */
public class FileStorageException extends RuntimeException {

    public FileStorageException(String message){
        super(message);
    }

    public FileStorageException(String message, Throwable cause){
        super(message,cause);
    }
}
