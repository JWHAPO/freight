package app.hapo.car.freight.service.user

import app.hapo.car.freight.domain.auth.email.EmailAuthRepository
import app.hapo.car.freight.domain.user.Role
import app.hapo.car.freight.domain.user.User
import app.hapo.car.freight.domain.user.UserRepository
import app.hapo.car.freight.domain.user.UserRole
import app.hapo.car.freight.service.auth.email.EmailAuthService
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

    @MockBean(name = "emailAuthRepository")
    EmailAuthRepository emailAuthRepository

    @MockBean(name = "emailAuthService")
    EmailAuthService emailAuthService

    def "save"(){
        given:

            List<UserRole> userRoles = Arrays.asList(new UserRole(2L, Role.MEMBER))
            User testUser = new User(4L,"email11@email.com","1234","Mr.Park",null,null,null,null,userRoles)

            given(userRepository.save(testUser))
                    .willReturn(testUser)

        when:
            Optional<User> resultUser = userService.save(testUser)

        then:
            resultUser.get() == testUser

    }

    def "findById" (){
        given:
            long id=2

            List<UserRole> userRoles = Arrays.asList(new UserRole(2L, Role.MEMBER))
            User testUser = new User(3L,"email@email.com","1234","Mr.Park",null,null,null,null,userRoles)

            given(userRepository.findById(id))
            .willReturn(Optional.of(testUser))

        when:
            Optional<User> resultUser = userService.findById(2)

        then:
            resultUser.get() == testUser

    }
}
