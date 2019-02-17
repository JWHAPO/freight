package app.hapo.car.freight.service.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification

/*
 * Created by hapo
 * Date : 19. 2. 17 오후 10:44
 * Description : 
 */
@SpringBootTest
class UserServiceTest extends Specification {

    @Autowired
    UserService userService

    @MockBean
}
