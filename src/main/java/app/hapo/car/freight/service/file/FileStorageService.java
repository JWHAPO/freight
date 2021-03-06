package app.hapo.car.freight.service.file;

import app.hapo.car.freight.domain.file.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * freight
 * Class: FileStorageService
 * Created by hapo on 2019-03-08.
 * Description:
 */
public interface FileStorageService {

    public File storeFile(MultipartFile file);

    public Resource loadFileAsResource(String fileName);
}
