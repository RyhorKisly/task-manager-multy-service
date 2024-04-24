package by.itacademy.auditservice.service.feign.factories;

import by.itacademy.auditservice.service.feign.impl.UserClientFallBackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallBackFactory implements FallbackFactory<UserClientFallBackImpl> {

    @Override
    public UserClientFallBackImpl create(Throwable cause) {
       return new UserClientFallBackImpl(cause);
    }
}
