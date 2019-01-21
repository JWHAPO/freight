package app.hapo.car.freight.service.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 11:06
 * Description :
 */

import app.hapo.car.freight.common.util.GenerateKey;
import app.hapo.car.freight.domain.auth.email.EmailAuth;
import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.domain.user.UserRepository;
import app.hapo.car.freight.service.auth.email.EmailAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public EmailAuthService emailAuthService;

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
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        Optional<User> checkedUser = findByEmail(user.getEmail());
        Optional<EmailAuth> checkedAuth = emailAuthService.findByEmail(user.getEmail());

        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        LocalDateTime expiredDateTime = currentLocalDateTime.plusDays(7);

        EmailAuth emailAuth = null;

        if(checkedUser.isPresent()){ //Email 주소가 이미 있는 경우
            if(checkedAuth.isPresent()){ //인증메일을 승인한 내역이 있으면 이미 있는 유저라고 알림.
                throw new RuntimeException("Already Have ID");
            }else{//인증메일을 보낸 적이 없는 경우.
                String authKey = GenerateKey.generateRandomKey(50);

                emailAuth = EmailAuth.builder()
                        .userId(checkedUser.get().getUserId())
                        .email(checkedUser.get().getEmail())
                        .authKey(authKey)
                        .expiredDate(expiredDateTime)
                        .isAuth("N")
                        .build();


                emailAuthService.save(emailAuth);
                sendEmail(checkedUser.get().getEmail(),authKey);
            }
        }else{
            String authKey = GenerateKey.generateRandomKey(50);
            checkedUser = Optional.of(userRepository.save(user));


            emailAuth = EmailAuth.builder()
                    .userId(checkedUser.get().getUserId())
                    .email(checkedUser.get().getEmail())
                    .authKey(authKey)
                    .expiredDate(expiredDateTime)
                    .isAuth("N")
                    .build();

            emailAuthService.save(emailAuth);
            sendEmail(checkedUser.get().getEmail(),authKey);
        }
        return checkedUser.get();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private void sendEmail(String emailAddress, String authKey){
        try {
            MimeMessage message = emailSender.createMimeMessage();
            message.setSubject("TITLE");
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setTo(emailAddress);
            helper.setText(getAuthEmailForm(emailAddress, authKey), true);
            emailSender.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getAuthEmailForm(String emailAddress, String authKey){
        StringBuffer authEmailContents = new StringBuffer();

        authEmailContents.append("<html>");
        authEmailContents.append("<h1> Hello My Users. </<h1>");
        authEmailContents.append("<h3> If you want to use this app, You try! </h3>");
        authEmailContents.append("<h3> This is Aour Authorization key: "+authKey+"</h3>");
        authEmailContents.append("<a href = \"http://220.71.48.107:8080/auth/email/"+emailAddress+"/"+authKey+"\">여기를 클릭</a>");
        authEmailContents.append("</html>");

        return authEmailContents.toString();
    }
}
