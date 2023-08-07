package by.itacademy.userservice.config;

import by.itacademy.userservice.core.converters.PageToPageDTOConverter;
import by.itacademy.userservice.core.formatters.LocalDateTimeToMilliFormatter;
import by.itacademy.userservice.core.converters.UserEntityToUserDTOConverter;
import by.itacademy.userservice.dao.repositories.IUserDao;
import by.itacademy.userservice.endponts.utils.JwtTokenHandler;
import by.itacademy.userservice.service.AuditInteractService;
import by.itacademy.userservice.service.UserService;
import by.itacademy.userservice.service.api.IAuditInteractService;
import by.itacademy.userservice.service.api.IUserService;
import by.itacademy.userservice.service.authentification.UserHolder;
import by.itacademy.userservice.service.feign.AuditServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserEntityToUserDTOConverter());
        registry.addConverter(new PageToPageDTOConverter());
        registry.addFormatter(new LocalDateTimeToMilliFormatter());
    }

    @Bean
    public IUserService getUserService(
            IUserDao userDao,
            PasswordEncoder encoder
    ) {
        return new UserService(userDao, encoder);
    }

    @Bean
    public IAuditInteractService getAuditInteractService(
            AuditServiceClient auditServiceClient,
            JwtTokenHandler jwtHandler,
            UserHolder holder
    ) {
        return new AuditInteractService(auditServiceClient, jwtHandler, holder);
    }
}
