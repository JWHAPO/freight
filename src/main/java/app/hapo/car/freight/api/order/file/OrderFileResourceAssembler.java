package app.hapo.car.freight.api.order.file;/*
 * Created by hapo
 * Date : 19. 3. 10 오전 1:12
 * Description :
 */

import app.hapo.car.freight.domain.order.file.OrderFile;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrderFileResourceAssembler implements ResourceAssembler<OrderFile, Resource<OrderFile>> {

    @Override
    public Resource<OrderFile> toResource(OrderFile orderFile) {
        return null;
    }
}
