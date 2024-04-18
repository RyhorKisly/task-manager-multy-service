package by.itacademy.userservice.service.authentification;

import by.itacademy.userservice.config.properites.AppProperties;
import by.itacademy.userservice.dao.entity.UserEntity;
import by.itacademy.userservice.dao.entity.VerificationEntity;
import by.itacademy.userservice.service.api.IEmailService;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;
    private final AppProperties appProperties;

    public EmailService(
            JavaMailSender javaMailSender,
            MailProperties mailProperties,
            AppProperties appProperties
    ) {
        this.javaMailSender = javaMailSender;
        this.mailProperties = mailProperties;
        this.appProperties = appProperties;
    }

    @Override
    @Async
    public void sendEmail(UserEntity item, VerificationEntity verificationEntity) {
        // SimpleMailMessage mailMessage = new SimpleMailMessage();
        // mailMessage.setFrom(mailProperties.getUsername());
        // mailMessage.setTo(item.getMail());
        // mailMessage.setSubject("Complete Registration!");
        // mailMessage.setText("To confirm your account, please click here : "
        //         + appProperties.getUserVerificationPath()
        //         + "?code=" + verificationEntity.getUuid() + "&mail=" + item.getMail());
        //
        // javaMailSender.send(mailMessage);
    }
}
