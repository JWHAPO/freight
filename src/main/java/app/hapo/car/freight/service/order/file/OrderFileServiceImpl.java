package app.hapo.car.freight.service.order.file;/*
 * Created by hapo
 * Date : 19. 3. 10 오전 1:08
 * Description :
 */

import app.hapo.car.freight.domain.order.file.OrderFile;
import app.hapo.car.freight.domain.order.file.OrderFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderFileServiceImpl implements OrderFileService {

    @Autowired
    OrderFileRepository orderFileRepository;

    @Override
    public List<OrderFile> findByOrderId(Long orderId) {
        return orderFileRepository.findByOrderId(orderId);
    }

    @Override
    public Optional<OrderFile> findById(Long id) {
        return orderFileRepository.findById(id);
    }

    @Override
    public List<OrderFile> findAll() {
        return orderFileRepository.findAll();
    }
}
