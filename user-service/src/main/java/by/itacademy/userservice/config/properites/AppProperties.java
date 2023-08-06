package by.itacademy.userservice.config.properites;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String userVerificationPath;

    public String getUserVerificationPath() {
        return userVerificationPath;
    }

    public void setUserVerificationPath(String userVerificationPath) {
        this.userVerificationPath = userVerificationPath;
    }
}
