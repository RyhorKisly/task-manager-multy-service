package by.itacademy.userservice.service.feign.factories;

import by.itacademy.userservice.service.feign.impl.AuditClientFallBackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditClientFallBackFactory implements FallbackFactory<AuditClientFallBackImpl> {

    @Override
    public AuditClientFallBackImpl create(Throwable cause) {
       return new AuditClientFallBackImpl(cause);
    }
}
