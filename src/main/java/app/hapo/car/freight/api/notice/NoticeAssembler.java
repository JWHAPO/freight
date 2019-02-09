package app.hapo.car.freight.api.notice;/*
 * Created by hapo
 * Date : 19. 2. 9 오후 11:22
 * Description : NoticeAssembler
 */

import app.hapo.car.freight.domain.notice.Notice;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class NoticeAssembler implements ResourceAssembler<Notice, Resource<Notice>> {
    @Override
    public Resource<Notice> toResource(Notice notice) {
        return null;
    }
}
