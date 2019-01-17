package app.hapo.car.freight.service.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:06
 * Description :
 */

import app.hapo.car.freight.common.util.GenerateKey;
import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public JavaMailSender emailSender;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            message.setSubject("TITLE");
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setText(getAuthEmailForm(), true);
            emailSender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }


        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    private String getAuthEmailForm(){
        String authKey = GenerateKey.generateRandomKey(50);

        StringBuffer authEmailContents = new StringBuffer();

        authEmailContents.append("<html>");
        authEmailContents.append("<h1> Hello My Users. </<h1>");
        authEmailContents.append("<h3> If you want to use this app, You try! </h3>");
        authEmailContents.append("<h3> This is Aour Authorization key: "+authKey+"</h3>");
        authEmailContents.append("<a href = \"file:///home/hapo/dev/work_space/test_html/test.html\">여기를 클릭</a>");
        authEmailContents.append("</html>");

        return authEmailContents.toString();
    }
}
