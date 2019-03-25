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
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserCarService userCarService;
    @Autowired
    UserResourceAssembler userResourceAssembler;

    @GetMapping(value = "/users")
    public Resources<Resource<User>> all(Pageable pageable){
        List<Resource<User>> users = userService.findAll(pageable).stream()
                                     .map(userResourceAssembler::toResource)
                                     .collect(Collectors.toList());

        return new Resources<>(users,
                linkTo(methodOn(UserController.class).all(pageable)).withSelfRel());
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> newUser(@RequestBody User newUser) throws URISyntaxException{
        Resource<User> resource = userResourceAssembler.toResource(userService.save(newUser).get());
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }


    @GetMapping(value = "/users/{id}")
    public Resource<User> findById(@PathVariable Long id){
        User user = userService.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return userResourceAssembler.toResource(user);
    }

    @GetMapping(value = "/users/{email}/{password}")
    public User findByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        return userService.findByEmailAndPassword(email, password).orElseThrow(()->new UserNotFoundException(email));
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) throws URISyntaxException{
        Optional<User> updatedUser = userService.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    return userService.save(user);
                })
                .orElseGet(()->{
                    newUser.setUserId(id);
                    return userService.save(newUser);
                });
        Resource<User> resource = userResourceAssembler.toResource(updatedUser.get());
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        User user = userService.findById(id).orElseThrow(()->new UserNotFoundException(id));
        userService.delete(user);
        return ResponseEntity.noContent().build();
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
