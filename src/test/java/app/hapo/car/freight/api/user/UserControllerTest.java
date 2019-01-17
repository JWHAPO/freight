package app.hapo.car.freight.api.user;/*
 * Created by hapo
 * Date : 19. 1. 7 오후 11:08
 * Description :
 */


import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.domain.usercar.UserCar;
import app.hapo.car.freight.service.user.UserService;
import app.hapo.car.freight.service.usercar.UserCarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserCarService userCarService;

    @Test
    public void findAllTest() throws Exception{
        User user = new User(1L,1L,"jw.kim@emaintec.com","1234","Mr.Kim","","",100L,1L);

        List<User> allUsers = Collections.singletonList(user);
        given(userService.findAll()).willReturn(allUsers);

        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].password",is(user.getPassword())))
                .andDo(document("users/findAll"));
    }

    @Test
    public void findByIdTest() throws Exception{
        User user = new User(1L,1L,"jw.kim@emaintec.com","1234","Mr.Kim","","",100L,1L);
        Optional<User> userOptional = Optional.of(user);

        given(userService.findById(1L)).willReturn(userOptional);

        mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email",is(user.getEmail())))
                .andDo(document("users/findById"));
    }

    @Test
    public void findByEmailAndPasswordTest() throws Exception{
        User user = new User(1L,1L,"jw.kim@emaintec.com","1234","Mr.Kim","","",100L,1L);
        given(userService.findByEmailAndPassword("jw.kim@emaintec.com","1234")).willReturn(user);

        mockMvc.perform(get("/users/jw.kim@emaintec.com/1234").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("users/findByEmailAndPassword"));
    }

    @Test
    public void createUserTest() throws Exception{
        User user = new User(1L,1L,"jw.kim@emaintec.com","1234","Mr.Kim","","",100L,1L);

        String userJson = objectMapper.writeValueAsString(user);
        System.out.println("userJson:::");
        System.out.println(userJson);


        given(userService.createUser(user)).willReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(document("users/createUser"))
                .andReturn();
    }

    @Test
    public void updateUserTest() throws Exception{
        User user = new User(1L,1L,"jw.kim@emaintec.com","1234","Mr.Kim","","",100L,1L);
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andDo(document("users/updateUser"))
                .andReturn();
    }

    @Test
    public void deleteUserTest() throws Exception{

        mockMvc.perform(delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserCarsTest() throws Exception{
        Car car = new Car(2L,2L,"newCar1", 160L,"cm",280L,"cm",180L,"cm",1100L,"Kg");
        UserCar userCar = new UserCar(1L,1L,2L,car);

        List<UserCar> allUserCars = Collections.singletonList(userCar);

        mockMvc.perform(get("/users/1/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("users/findUserCars"));
    }
}
