package app.hapo.car.freight.api.user


import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

/**
 * freight
 * Class: UserControllerTest
 * Created by hapo on 2019-02-22.
 * Description: 
 */
@WebAppConfiguration
class UserControllerTest extends Specification {

    def userController = new UserController()

    MockMvc mockMvc

    //@Before
    def setup(){
        mockMvc = standaloneSetup(userController).build()
    }

    def "Test find all"(){
        setup:
        def token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYWExQGVtYWludGVjLmNvbSIsInNjb3BlcyI6WyJST0xFX01FTUJFUiJdLCJpc3MiOiJoYXBvIiwiaWF0IjoxNTUwOTEyNDgxLCJleHAiOjE1NTA5MTMzODF9.jJ0554eqIGHFgTySoOklZfhk8Wu5CUSLcEiGbpKKO0yhkiiLVeHy6_xfkTIwEL5pVxB2g_of8GbJF_nowSHT1Q"
        def request = get("/api/users")
        request.header("Authorization","Bearer $token")

        when:
        def response = mockMvc.perform(get("/api/users"))

        then:
        response.andExpect(status().isOk())
    }
}
