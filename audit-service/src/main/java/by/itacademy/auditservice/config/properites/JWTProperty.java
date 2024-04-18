package by.itacademy.auditservice.config.properites;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JWTProperty {
    private String secret;
    private String issuer;
    private String user;
    private String system;
}
