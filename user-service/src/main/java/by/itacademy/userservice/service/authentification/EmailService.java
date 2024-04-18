package by.itacademy.userservice.service.authentification;

import by.itacademy.userservice.config.properites.AppProperties;
import by.itacademy.userservice.core.dto.UserDTO;
import by.itacademy.userservice.core.dto.VerificationDTO;
import by.itacademy.userservice.service.api.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;
    private final AppProperties appProperties;

    @Override
    @Async
    public void sendEmail(UserDTO item, VerificationDTO verificationDTO) {
        // SimpleMailMessage mailMessage = new SimpleMailMessage();
        // mailMessage.setFrom(mailProperties.getUsername());
        // mailMessage.setTo(item.getMail());
        // mailMessage.setSubject("Complete Registration!");
        // mailMessage.setText("To confirm your account, please click here : "
        //         + appProperties.getUserVerificationPath()
        //         + "?code=" + verificationDTO.uuid() + "&mail=" + item.getMail());
        //
        // javaMailSender.send(mailMessage);
    }
}
