package by.itacademy.taskservice.config;

import by.itacademy.taskservice.core.converters.ProjectEntityToDTOConverter;
import by.itacademy.taskservice.core.converters.TaskEntityToDTOConverter;
import by.itacademy.taskservice.core.formatters.LocalDateTimeToMilliFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ProjectEntityToDTOConverter());
        registry.addConverter(new TaskEntityToDTOConverter());
        registry.addFormatter(new LocalDateTimeToMilliFormatter());
    }
}
