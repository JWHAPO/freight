package app.hapo.car.freight.api.user

import app.hapo.car.freight.AuthorizedControllerHelper
import app.hapo.car.freight.WebSecurityConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.http.HttpStatus.*

/**
 * freight
 * Class: UserControllerTest
 * Created by hapo on 2019-02-22.
 * Description: 
 */
@WebAppConfiguration
@ContextConfiguration(classes = WebSecurityConfig.class)
class UserControllerTest extends Specification {

//    @Autowired
//    WebApplicationContext context
//    MockMvc mockMvc
//
//    //@Before
//    def setup(){
//        mockMvc = standaloneSetup(new UserController()).build()
//
//        def tokenResponse = mockMvc.perform(post("/auth/login")).andReturn().response
//    }
//
//    def "Test find all"(){
//        setup:
//        def response = mockMvc.perform(get("/api/users")).andReturn().response
//
//        when:
//        MockHttpSession session = AuthorizedControllerHelper.buildSecuritySession(context, "aaa1@emaintec.com")
//
//        then:
//        response.status == OK.value()
//    }
}
