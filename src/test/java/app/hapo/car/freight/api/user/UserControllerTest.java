package app.hapo.car.freight.api.user;/*
 * Created by hapo
 * Date : 19. 1. 7 오후 11:08
 * Description :
 */


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void findAllTest() throws Exception{
        User user = new User(1L,"kjw@naver.com","123","Mr.KKK","Seoul","2");

        List<User> allUsers = Collections.singletonList(user);
        given(userService.findAll()).willReturn(allUsers);

        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].password",is(user.getPassword())));
    }

    @Test
    public void findByEmailAndPassword() throws Exception{
        User user = new User(1L,"kjw@naver.com","123","Mr.KKK","Seoul","2");
        given(userService.findByEmailAndPassword("kjw@naver.com","123")).willReturn(user);

        mockMvc.perform(get("/users/kjw@naver.com/123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name",is(user.getName())));


    }
}
