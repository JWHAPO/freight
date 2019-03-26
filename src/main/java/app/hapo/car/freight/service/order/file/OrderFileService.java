package app.hapo.car.freight.service.order.file;/*
 * Created by hapo
 * Date : 19. 3. 10 오전 1:07
 * Description :
 */

import app.hapo.car.freight.domain.order.file.OrderFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderFileService {
    Page<OrderFile> findByOrderId(Long orderId, Pageable pageable);
    Optional<OrderFile> findById(Long id);
    Page<OrderFile> findAll(Pageable pageable);
}
