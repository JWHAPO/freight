package app.hapo.car.freight.service.grade;/*
 * Created by hapo
 * Date : 19. 3. 4 오후 11:02
 * Description :
 */

import app.hapo.car.freight.domain.grade.Grade;
import app.hapo.car.freight.domain.grade.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GradeServiceImpl implements GradeService{

    @Autowired
    GradeRepository gradeRepository;

    @Override
    public Page<Grade> findAll(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        return gradeRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Grade> findById(Long id) {
        return gradeRepository.findById(id);
    }
}
