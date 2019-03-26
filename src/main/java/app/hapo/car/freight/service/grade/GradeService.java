package app.hapo.car.freight.service.grade;/*
 * Created by hapo
 * Date : 19. 3. 4 오후 10:58
 * Description : service interface associated with rating
 */

import app.hapo.car.freight.domain.grade.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GradeService {

    Page<Grade> findAll(Pageable pageable);

    Optional<Grade> findById(Long id);
}
