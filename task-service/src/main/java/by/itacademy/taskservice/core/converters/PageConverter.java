package by.itacademy.taskservice.core.converters;

import by.itacademy.sharedresource.core.dto.PageDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageConverter {
    private final ConversionService conversionService;

    public PageConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    public <E, D> PageDTO<D> convertToPageDTO(Page<E> page, Class<D> dtoClass) {
        return new PageDTO<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.getNumberOfElements(),
                page.isLast(),
                page.getContent().stream().map(e -> conversionService.convert(e, dtoClass)).toList()
        );

    }
}
