package app.hapo.car.freight.service.grade;/*
 * Created by hapo
 * Date : 19. 3. 4 오후 11:02
 * Description :
 */

import app.hapo.car.freight.domain.grade.Grade;
import app.hapo.car.freight.domain.grade.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GradeServiceImpl implements GradeService{

    @Autowired
    GradeRepository gradeRepository;

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Grade> findById(Long id) {
        return gradeRepository.findById(id);
    }
}
