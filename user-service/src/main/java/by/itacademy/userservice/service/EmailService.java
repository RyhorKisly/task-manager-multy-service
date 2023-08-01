package by.itacademy.userservice.service;

import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.entity.VerificationTokenEntity;
import by.itacademy.userservice.service.api.IEmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendEmail(UserEntity item, VerificationTokenEntity token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(item.getMail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/users/verification?code=" + token.getToken() + "&mail=" + item.getMail());

        javaMailSender.send(mailMessage);
    }
}
