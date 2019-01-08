package app.hapo.car.freight.api.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:30
 * Description :
 */

import app.hapo.car.freight.domain.car.Car;
import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.domain.usercar.UserCar;
import app.hapo.car.freight.service.car.CarService;
import app.hapo.car.freight.service.user.UserService;
import app.hapo.car.freight.service.usercar.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    UserCarService userCarService;

    @GetMapping(value = "/{email}/{password}")
    public User findByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        return userService.findByEmailAndPassword(email, password);
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<User> findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if(!userOptional.isPresent()) return ResponseEntity.notFound().build();

        user.setId(id);
        userService.createUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping(value = "/{id}/cars")
    public List<Car> findUserCars(@PathVariable Long id){
        //UserCar에서 데이터 가져오기.
        //UserCar의 데이터로 Car에 있는 데이터 가져오기.
        //수정중.
        return null;
    }


}
