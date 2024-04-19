package by.itacademy.auditservice.endpoints.web;

import by.itacademy.auditservice.core.dto.AuditDTO;
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

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AuditController {
    private final IAuditService auditService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<Page<AuditDTO>> getPages(
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer page,
            @RequestParam(required = false, defaultValue = "20") @PositiveOrZero Integer size
    ) {
        Page<AuditDTO> pageOfAudit =  auditService.get(PageRequest.of(page, size));

        return new ResponseEntity<>(pageOfAudit, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AuditDTO> getCard(@PathVariable UUID uuid) {
        AuditDTO auditDTO = conversionService.convert(auditService.get(uuid), AuditDTO.class);
        return new ResponseEntity<>(auditDTO, HttpStatus.OK);
    }


}
