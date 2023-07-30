package by.itacademy.auditservice.config;

import by.itacademy.auditservice.core.converters.AuditEntityToAuditDTOConverter;
import by.itacademy.auditservice.core.converters.PageToPageDTOConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new AuditEntityToAuditDTOConverter());
        registry.addConverter(new PageToPageDTOConverter());

    }
}
