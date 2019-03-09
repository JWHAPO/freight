package app.hapo.car.freight.service.order.file;/*
 * Created by hapo
 * Date : 19. 3. 10 오전 1:07
 * Description :
 */

import app.hapo.car.freight.domain.order.file.OrderFile;

import java.util.List;
import java.util.Optional;

public interface OrderFileService {
    List<OrderFile> findByOrderId(Long orderId);
    Optional<OrderFile> findById(Long id);
    List<OrderFile> findAll();
}
