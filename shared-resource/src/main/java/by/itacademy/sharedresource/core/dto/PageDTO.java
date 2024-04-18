package by.itacademy.sharedresource.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDTO<T> {
    private Integer number;
    private Integer size;
    private Integer totalPage;
    private Long totalElements;
    private boolean first;
    private Integer numberOfElements;
    private boolean last;
    private List<T> content;
}
