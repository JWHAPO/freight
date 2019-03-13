package app.hapo.car.freight.domain.file;

import app.hapo.car.freight.domain.common.AuditModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * freight
 * Class: UploadFileResponse
 * Created by hapo on 2019-03-08.
 * Description:
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ta2file")
public class File extends AuditModel {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_ext")
    private String fileExt;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "download_uri")
    private String downloadUri;

    @Column(name = "content_type")
    private String contentType;

    @Builder
    public File(String fileName, String fileExt, Long fileSize, String downloadUri, String contentType) {
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.fileSize = fileSize;
        this.downloadUri = downloadUri;
        this.contentType = contentType;
    }
}
