package app.hapo.car.freight.api.user;/*
 * Created by hapo
 * Date : 19. 1. 7 오후 11:08
 * Description :
 */


import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.service.user.UserService;
import app.hapo.car.freight.service.usercar.UserCarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;
    @MockBean
    UserCarService userCarService;


    @Test
    public void findAllTest() throws Exception{
        User user = new User(1L,1L,"kjw@naver.com","123","Mr.KKK","Seoul",1L);

        List<User> allUsers = Collections.singletonList(user);
        given(userService.findAll()).willReturn(allUsers);

        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].password",is(user.getPassword())))
                .andDo(document("users/findAll"));
    }

    @Test
    public void findByEmailAndPasswordTest() throws Exception{
        User user = new User(1L,1L,"mrKim4@email.com","1234","Mr.Kim","Seoul",1L);
        given(userService.findByEmailAndPassword("mrKim4@email.com","1234")).willReturn(user);

        mockMvc.perform(get("/users/mrKim4@email.com/1234").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name",is(user.getName())))
                .andDo(document("users/findByEmailAndPassword"));


    }
}
