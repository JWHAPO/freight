package app.hapo.car.freight.service.user

import app.hapo.car.freight.domain.user.Role
import app.hapo.car.freight.domain.user.User
import app.hapo.car.freight.domain.user.UserRepository
import app.hapo.car.freight.domain.user.UserRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification

import static org.mockito.BDDMockito.*

/*
 * Created by hapo
 * Date : 19. 2. 17 오후 10:44
 * Description : 
 */
@SpringBootTest
class UserServiceTest extends Specification {

    @Autowired
    UserService userService

    @MockBean(name = "userRepository")
    UserRepository userRepository

    def "findById" (){
        given:
        long id=2

        List<UserRole> userRoles = Arrays.asList(new UserRole(2L, Role.MEMBER))

        given(userRepository.findById(id))
        .willReturn(Optional.of(new User(3L,"email@email.com","1234","Mr.Park",null,null,null,null,userRoles)))


        when:
        Optional<User> user = userService.findById(2)

        then:

        user.get().password == "1234"

    }
}
