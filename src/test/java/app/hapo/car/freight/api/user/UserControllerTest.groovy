package app.hapo.car.freight.api.user

import app.hapo.car.freight.AuthorizedControllerHelper
import app.hapo.car.freight.WebSecurityConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
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
@SpringBootTest
@ContextConfiguration
class UserControllerTest extends Specification {
//
//    @Autowired
//    WebApplicationContext context
//
//    MockMvc mockMvc = standaloneSetup(new UserController()).build()
//
//    //@Before
//    def setup(){
//    }
//
//    def "Test find all"(){
//
//        setup:
//        def request = post("/auth/login")
//        request.contentType(MediaType.APPLICATION_JSON_UTF8)
//        request.content("{\n" +
//                "    \"email\": \"aaa1@emaintec.com\",\n" +
//                "    \"password\": \"1234\"\n" +
//                "}")
//        request.accept("application/json;charset=UTF-8")
//
//        when:
//            def response = mockMvc.perform(request).andReturn().response
//            def result = response.contentAsString
//            println(result)
//        then:
//        response.status == OK.value()
//    }
}
