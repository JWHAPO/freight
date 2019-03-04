package app.hapo.car.freight.service.grade;/*
 * Created by hapo
 * Date : 19. 3. 4 오후 10:58
 * Description : service interface associated with rating
 */

import app.hapo.car.freight.domain.grade.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {

    List<Grade> findAll();

    Optional<Grade> findById(Long id);
}
