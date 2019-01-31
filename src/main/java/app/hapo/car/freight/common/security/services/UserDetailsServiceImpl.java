package app.hapo.car.freight.common.security.services;

import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * freight
 * Class: UserDetailServiceImpl
 * Created by hapo on 2019-01-31.
 * Description: UserDetailServiceImpl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException(
                        "User Not Found with -> email : "+email));

        return UserPrinciple.build(user);
    }
}
