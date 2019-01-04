package app.hapo.car.freight.api.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:30
 * Description :
 */

import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{email}/{password}", method = RequestMethod.GET)
    public User findByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        User user = userService.findByEmailAndPassword(email, password);
        return user;
    }
}
