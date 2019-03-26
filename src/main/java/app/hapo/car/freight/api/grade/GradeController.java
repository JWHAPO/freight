package app.hapo.car.freight.api.grade;/*
 * Created by hapo
 * Date : 19. 3. 4 오후 11:07
 * Description :
 */

import app.hapo.car.freight.domain.grade.Grade;
import app.hapo.car.freight.service.grade.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class GradeController {

    @Autowired
    GradeService gradeService;
    @Autowired
    GradeResourceAssembler gradeResourceAssembler;

    @GetMapping(value = "/grades")
    public Resources<Resource<Grade>> findAll(Pageable pageable){
        List<Resource<Grade>> grades = gradeService.findAll(pageable).stream()
                .map(gradeResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(grades,
                linkTo(methodOn(GradeController.class).findAll(pageable)).withSelfRel());
    }

    @GetMapping(value = "/grades/{id}")
    public Resource<Grade> findById(@PathVariable Long id){
        return gradeResourceAssembler.toResource(
                gradeService.findById(id)
                .orElseThrow(()->new GradeNotFoundException(id))
        );
    }

    class GradeNotFoundException extends RuntimeException{
        public GradeNotFoundException(Long id) { super("Could not find grade: "+id);}
    }
    @ControllerAdvice
    class GradeNotFoundExceptionAdvice{
        @ResponseBody
        @ExceptionHandler(GradeController.GradeNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String gradeNotFoundHandler(GradeController.GradeNotFoundException ex) {return ex.getMessage();}
    }
}
