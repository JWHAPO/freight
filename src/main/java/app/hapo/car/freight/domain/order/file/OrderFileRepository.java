package app.hapo.car.freight.domain.order.file;/*
 * Created by hapo
 * Date : 19. 3. 9 오후 7:09
 * Description :
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFileRepository extends JpaRepository<OrderFile,Long> {
    Page<OrderFile> findByOrderId(Long orderId, Pageable pageable);
}
