package app.hapo.car.freight.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/*
 * Created by hapo
 * Date : 19. 2. 17 오후 10:29
 * Description : 
 */
@SpringBootTest
class UserRepositoryTest extends Specification {

    @Autowired
    UserRepository userRepository;

    // @Before
    def setup(){

        UserRole userRole1 = new UserRole(3L,Role.MEMBER)
        List<UserRole> userRoles = Arrays.asList(userRole1)

        userRepository.save(new User(3L,"aaa@emaintec.com","1234","Kim","01012341234","",100L,1L,userRoles))
    }

    // @After
    def cleanup(){
        userRepository.deleteAll()
    }

    def "findAll" (){
        when:
            List<User> users = userRepository.findAll()
        then:
            users.size() == 1
            users.get(0).email == "aaa@emaintec.com"
            users.get(0).password == "1234"
    }


}
