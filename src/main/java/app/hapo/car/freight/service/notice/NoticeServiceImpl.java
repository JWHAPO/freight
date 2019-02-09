package app.hapo.car.freight.service.notice;/*
 * Created by hapo
 * Date : 19. 2. 9 오후 11:18
 * Description : NoticeServiceImpl
 */

import app.hapo.car.freight.domain.notice.Notice;
import app.hapo.car.freight.domain.notice.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {


    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Optional<Notice> findById(Long id) {
        return noticeRepository.findById(id);
    }
}
