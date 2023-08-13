package by.itacademy.userservice.config.properites;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String userVerificationPath;
    private String auditUrl;

    public String getUserVerificationPath() {
        return userVerificationPath;
    }

    public void setUserVerificationPath(String userVerificationPath) {
        this.userVerificationPath = userVerificationPath;
    }

    public String getAuditUrl() {
        return auditUrl;
    }

    public void setAuditUrl(String auditUrl) {
        this.auditUrl = auditUrl;
    }
}
