package app.hapo.car.freight.common.file;/*
 * Created by hapo
 * Date : 19. 3. 7 오전 12:32
 * Description :
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
}
