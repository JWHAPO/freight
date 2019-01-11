package app.hapo.car.freight.api.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:30
 * Description :
 */

import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.domain.usercar.UserCar;
import app.hapo.car.freight.service.user.UserService;
import app.hapo.car.freight.service.usercar.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserCarService userCarService;

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<User> findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping(value = "/{email}/{password}")
    public User findByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        return userService.findByEmailAndPassword(email, password);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user,UriComponentsBuilder builder) {
        User savedUser = userService.createUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(savedUser.getUserId()).toUri());
        return new ResponseEntity<User>(httpHeaders,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if(!userOptional.isPresent()) return ResponseEntity.notFound().build();

        user.setUserId(id);
        user.setAddress("Guri");
        userService.createUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping(value = "/{id}/cars")
    public List<UserCar> findUserCars(@PathVariable Long id){
        List<UserCar> userCars = userCarService.findByUserId(id);
        return userCars;
    }


}
