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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailAuthService emailAuthService;
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
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
    public Optional<User> findByEmailHavingAuth(String email) {
        Optional<EmailAuth> checkedAuth = emailAuthService.findByEmailAndIsAuth(email,"Y");
        Optional<User> checkedUser = findByEmail(email);

        if(checkedAuth.isPresent()) return checkedUser;
        else return Optional.empty();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Optional<User> save(User user) {

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
        return checkedUser;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
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
        authEmailContents.append("<head>");
        authEmailContents.append("<style type=\"text/css\">");
        authEmailContents.append("body{text-align:center;}");

        authEmailContents.append("img{");
        authEmailContents.append("width: 100px;");
        authEmailContents.append("height: 100px");
        authEmailContents.append("float:center;");
        authEmailContents.append("}");

        authEmailContents.append(".btncls{");
        authEmailContents.append("background-color:#5587ED;");
        authEmailContents.append("border: #5587ED;");
        authEmailContents.append("font-family: Arial, Geneva, Arial, Helvetica,  sans-serif;");
        authEmailContents.append("font-size: 15px;");
        authEmailContents.append("color: #fff;");
        authEmailContents.append("letter-spacing: 1px;");
        authEmailContents.append("padding: 8px 12px;");
        authEmailContents.append("font-size: 14px;");
        authEmailContents.append("font-weight: normal;");

        authEmailContents.append("border-radius: 4px;");
        authEmailContents.append("line-height: 1.5;");
        authEmailContents.append("text-decoration:none;");
        authEmailContents.append("}");


        authEmailContents.append("</style>");
        authEmailContents.append("</head>");
        authEmailContents.append("<body>");
        authEmailContents.append("<img src=\"tracking.png\" align=\"center\">");

        authEmailContents.append("<h1> Hello. </h1>");
        authEmailContents.append("<h3> Click and confirm that you want to sign in to Freight.</h3>");
        authEmailContents.append("<h3> This link will expire in five minutes.</h3>");
        authEmailContents.append("<br />");


        authEmailContents.append("<a href = \"http://localhost:8080/auth/email/"+emailAddress+"/"+authKey+"\">여기를 클릭</a>");

        authEmailContents.append("<button class=\"btncls\" onclick=\"location.href = 'http://localhost:8080/auth/email/"+emailAddress+"/"+authKey+"'\">SIGN IN TO FREIGHT</button>");


        authEmailContents.append("<br />");
        authEmailContents.append("<br />");
        authEmailContents.append("<br />");
        authEmailContents.append("<h5> If you are having any issues with your account, </h5>");
        authEmailContents.append("<h5> Please don't hesitate to contact us replying to this mail.</h5>");
        authEmailContents.append("<h5> Thank you.</h5>");

        authEmailContents.append("</body>");

        return authEmailContents.toString();
    }
}
