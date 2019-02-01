package app.hapo.car.freight.api.car;/*
 * Created by hapo
 * Date : 19. 1. 7 오후 10:01
 * Description :
 */

import app.hapo.car.freight.WebSecurityConfig;
import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.service.car.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
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

//@RunWith(SpringRunner.class)
//@WebMvcTest(CarController.class)
//@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CarControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    CarService carService;
//
//
//    @Test
//    public void findAllTest() throws Exception{
//        Car car = new Car(1L,"newCar1", 160L,"cm",280L,"cm",180L,"cm",1100L,"Kg");
//
//        List<Car> allCars = Collections.singletonList(car);
//        given(carService.findAll()).willReturn(allCars);
//
//        mockMvc.perform(get("/cars")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].carNo",is(car.getCarNo().intValue())))
//                .andDo(document("cars/findAll"));
//    }
}
