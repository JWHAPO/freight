package app.hapo.car.freight.service.notice;/*
 * Created by hapo
 * Date : 19. 2. 9 오후 11:17
 * Description : NoticeService
 */

import app.hapo.car.freight.domain.notice.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    List<Notice> findAll();
    Optional<Notice> findById(Long id);

}
