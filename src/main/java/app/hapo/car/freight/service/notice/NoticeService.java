package app.hapo.car.freight.service.notice;/*
 * Created by hapo
 * Date : 19. 2. 9 오후 11:17
 * Description : NoticeService
 */

import app.hapo.car.freight.domain.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NoticeService {

    Page<Notice> findAll(Pageable pageable);
    Optional<Notice> findById(Long id);

}
