package by.itacademy.auditservice.endpoints.web;

import by.itacademy.auditservice.core.dto.AuditDTO;
import by.itacademy.auditservice.endpoints.web.api.AuditControllerApi;
import by.itacademy.auditservice.service.api.IAuditService;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController implements AuditControllerApi {
    private final IAuditService auditService;
    private final ConversionService conversionService;

    @Override
    public ResponseEntity<Page<AuditDTO>> getPages(Integer page, Integer size) {
        Page<AuditDTO> pageOfAudit =  auditService.get(PageRequest.of(page, size));
        return new ResponseEntity<>(pageOfAudit, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuditDTO> getCard(UUID uuid) {
        AuditDTO auditDTO = conversionService.convert(auditService.get(uuid), AuditDTO.class);
        return new ResponseEntity<>(auditDTO, HttpStatus.OK);
    }


}
