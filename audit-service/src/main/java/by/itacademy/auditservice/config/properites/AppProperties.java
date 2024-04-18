package by.itacademy.auditservice.config.properites;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String userUrl;
}
