package app.hapo.car.freight.api.user

import app.hapo.car.freight.WebSecurityConfig
import app.hapo.car.freight.service.user.UserService
import app.hapo.car.freight.service.usercar.UserCarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.runner.WebApplicationContextRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity

/**
 * freight
 * Class: UserControllerTest
 * Created by hapo on 2019-02-22.
 * Description: 
 */
@ContextConfiguration(classes = [UserController, WebSecurityConfig])
@WebAppConfiguration
class UserControllerTest extends Specification {

    @Autowired
    WebApplicationContext context

    def userController = new UserController()

    MockMvc mockMvc

    @Autowired
    UserResourceAssembler userResourceAssembler

    @Autowired
    UserService userService

    @Autowired
    UserCarService userCarService

    //@Before
    def setup(){
        mockMvc = webAppContextSetup(context).apply(springSecurity()).build()
    }

    def "Test find all"(){
        setup:
        def request = get("/api/users")
        request.param("name","Kim")

        when:
        def response = mockMvc.perform(get("/api/users"))

        then:
        response.andExpect(status().isOk())
    }
}
