package by.itacademy.userservice.config;

import by.itacademy.userservice.core.converters.PageToPageDTOConverter;
import by.itacademy.userservice.core.formatters.LocalDateTimeToMilliFormatter;
import by.itacademy.userservice.core.converters.UserEntityToUserDTOConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserEntityToUserDTOConverter());
        registry.addConverter(new PageToPageDTOConverter());
        registry.addFormatter(new LocalDateTimeToMilliFormatter());
    }
}
