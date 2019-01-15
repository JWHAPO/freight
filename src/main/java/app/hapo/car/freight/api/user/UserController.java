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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public User findById(@PathVariable Long id){
        return userService.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @GetMapping(value = "/{email}/{password}")
    public User findByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        return userService.findByEmailAndPassword(email, password);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping(value = "/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id){

        return userService.findById(id).map(user-> {
            user.setName(newUser.getName());
            return userService.createUser(user);
        })
                .orElseGet(() ->{
                    newUser.setUserId(id);
                    return userService.createUser(newUser);
                });
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping(value = "/{id}/cars")
    public List<UserCar> findUserCars(@PathVariable Long id){
        return userCarService.findByUserId(id);
    }


    class UserNotFoundException extends RuntimeException{

        public UserNotFoundException(Long id) {
            super("Could not find user " + id);
        }
    }

    @ControllerAdvice
    class UserNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(UserNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String employeeNotFoundHandler(UserNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
