package app.hapo.car.freight.api.notice;/*
 * Created by hapo
 * Date : 19. 2. 9 오후 11:22
 * Description : NoticeResourceAssembler
 */

import app.hapo.car.freight.domain.notice.Notice;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class NoticeResourceAssembler implements ResourceAssembler<Notice, Resource<Notice>> {
    @Override
    public Resource<Notice> toResource(Notice notice) {
        return new Resource<>(notice,
                linkTo(methodOn(NoticeController.class).findById(notice.getNoticeId())).withSelfRel()
        );
    }
}
