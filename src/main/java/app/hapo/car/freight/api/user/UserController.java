package app.hapo.car.freight.api.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:30
 * Description :
 */

import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{email}/{password}", method = RequestMethod.GET)
    public User findByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        return userService.findByEmailAndPassword(email, password);
    }

    @RequestMapping(value = "/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


}
