package by.itacademy.auditservice;

import by.itacademy.auditservice.config.properites.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableConfigurationProperties(JWTProperty.class)
public class AuditServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditServiceApplication.class, args);
    }

}
