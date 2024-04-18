package by.itacademy.taskservice.config.properites;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String auditUrl;
    private String userUrl;
}
