package app.hapo.car.freight.api.grade;/*
 * Created by hapo
 * Date : 19. 3. 4 오후 11:16
 * Description :
 */

import app.hapo.car.freight.domain.grade.Grade;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class GradeResourceAssembler implements ResourceAssembler<Grade, Resource<Grade>> {
    @Override
    public Resource<Grade> toResource(Grade grade) {
        return new Resource<>(grade,
                linkTo(methodOn(GradeController.class).findById(grade.getGradeId())).withSelfRel(),
                linkTo(methodOn(GradeController.class).findAll()).withRel("grades"));
    }
}
