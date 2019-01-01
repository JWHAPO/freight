package app.hapo.car.freight.service.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:06
 * Description :
 */

import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }
}
