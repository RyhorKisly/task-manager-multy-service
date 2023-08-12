package by.itacademy.taskservice.config;

import by.itacademy.taskservice.core.converters.PageToPageDTOConverter;
import by.itacademy.taskservice.core.converters.ProjectEntityToProjectDTOConverter;
import by.itacademy.taskservice.core.formatters.LocalDateTimeToMilliFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ProjectEntityToProjectDTOConverter());
        registry.addConverter(new PageToPageDTOConverter());
        registry.addFormatter(new LocalDateTimeToMilliFormatter());
    }
}
