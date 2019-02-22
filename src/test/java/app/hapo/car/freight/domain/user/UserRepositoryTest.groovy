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
    UserRepository userRepository

    // @Before
    def setup(){

        List<UserRole> userRoles1 = Arrays.asList(new UserRole(3L,Role.MEMBER))
        List<UserRole> userRoles2 = Arrays.asList(new UserRole(4L,Role.MEMBER))
        List<UserRole> userRoles3 = Arrays.asList(new UserRole(5L,Role.MEMBER))

        userRepository.save(new User(3L,"aaa1@emaintec.com","1234","Kim1","01012341234","",100L,1L,userRoles1))
        userRepository.save(new User(4L,"aaa2@emaintec.com","1234","Kim2","01012341234","",100L,1L,userRoles2))
        userRepository.save(new User(5L,"aaa3@emaintec.com","1234","Kim3","01012341234","",100L,1L,userRoles3))
    }

    // @After
    def cleanup(){
//        userRepository.deleteAll()
    }

    def "findAll" (){
        when:
            List<User> users = userRepository.findAll()
        then:
            users.size() == 3
            users.get(0).email == "aaa1@emaintec.com"
            users.get(0).password == "1234"
    }

    def "findById" (){
        when:
        Optional<User> user = userRepository.findByEmail("aaa1@emaintec.com")
        Optional<User> emptyUser = userRepository.findByEmail("aaa2@emaintec.com")
        then:
            user.isPresent() == true
            user.get().name == "Kim1"

            emptyUser.isPresent() == false
            emptyUser == Optional.empty()

    }


}
