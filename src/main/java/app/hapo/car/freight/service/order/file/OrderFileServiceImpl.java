package app.hapo.car.freight.service.order.file;/*
 * Created by hapo
 * Date : 19. 3. 10 오전 1:08
 * Description :
 */

import app.hapo.car.freight.domain.order.file.OrderFile;
import app.hapo.car.freight.domain.order.file.OrderFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrderFileServiceImpl implements OrderFileService {

    @Autowired
    OrderFileRepository orderFileRepository;

    @Override
    public Page<OrderFile> findByOrderId(Long orderId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        return orderFileRepository.findByOrderId(orderId,pageRequest);
    }

    @Override
    public Optional<OrderFile> findById(Long id) {
        return orderFileRepository.findById(id);
    }

    @Override
    public Page<OrderFile> findAll(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        return orderFileRepository.findAll(pageRequest);
    }
}
