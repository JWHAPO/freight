package app.hapo.car.freight.domain.common;/*
 * Created by hapo
 * Date : 19. 3. 13 오후 11:31
 * Description :
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdTimeAt","updatedTimeAt"},
        allowGetters = true
)
public abstract class AuditModel implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdTimeAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time_at", nullable = false)
    @LastModifiedDate
    private Date updatedTimeAt;


}
