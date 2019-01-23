package app.hapo.car.freight.api.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:30
 * Description : User Controller -> 사용자에 대한 정보
 */

import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.domain.usercar.UserCar;
import app.hapo.car.freight.service.user.UserService;
import app.hapo.car.freight.service.usercar.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserCarService userCarService;

    @GetMapping("/users")
    public Resources<Resource<User>> all(){

        List<Resource<User>> users = userService.findAll().stream()
                .map(user -> new Resource<>(user,
                        linkTo(methodOn(UserController.class).findById(user.getUserId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).findByEmailAndPassword(user.getEmail(),user.getPassword())).withRel("emailPassword"),
                        linkTo(methodOn(UserController.class).all()).withRel("users")))
                .collect(Collectors.toList());

        return new Resources<>(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User user) {
        return userService.save(user).orElseThrow(()->new UserNotFoundException(user.getUserId()));
    }

    @GetMapping("/users/{id}")
    public Resource<User> findById(@PathVariable Long id){

        User user = userService.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return new Resource<>(user,
                linkTo(methodOn(UserController.class).findById(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }

    @GetMapping("/users/{email}/{password}")
    public User findByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        return userService.findByEmailAndPassword(email, password).orElseThrow(()->new UserNotFoundException(email));
    }

    @PutMapping("users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id){

        return userService.findById(id).map(user-> {
            user.setName(newUser.getName());
            return userService.save(user).get();
        })
        .orElseGet(() ->{
            newUser.setUserId(id);
            return userService.save(newUser).get();
        });
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping("/users/{id}/cars")
    public List<UserCar> findUserCars(@PathVariable Long id){
        return userCarService.findByUserId(id);
    }


    class UserNotFoundException extends RuntimeException{

        public UserNotFoundException(Long id) {
            super("Could not find user " + id);
        }
        public UserNotFoundException(String email) {
            super("Could not find user " + email);
        }
    }

    @ControllerAdvice
    class UserNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(UserNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String userNotFoundHandler(UserNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
