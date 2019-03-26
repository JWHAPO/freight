package app.hapo.car.freight.service.notice;/*
 * Created by hapo
 * Date : 19. 2. 9 오후 11:18
 * Description : NoticeServiceImpl
 */

import app.hapo.car.freight.domain.notice.Notice;
import app.hapo.car.freight.domain.notice.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {


    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public Page<Notice> findAll(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        return noticeRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Notice> findById(Long id) {
        return noticeRepository.findById(id);
    }
}
