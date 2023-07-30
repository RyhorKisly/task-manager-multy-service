package by.itacademy.auditservice.core.dto;

import java.util.List;

public class PageDTO<T> {
    private Integer number;
    private Integer size;
    private Integer totalPage;
    private Long totalElements;
    private boolean first;
    private Integer numberOfElements;
    private boolean last;
    private List<T> content;

    public PageDTO() {
    }

    public PageDTO(
            Integer number,
            Integer size,
            Integer totalPage,
            Long totalElements,
            boolean first,
            Integer numberOfElements,
            boolean last,
            List<T> content
    ) {
        this.number = number;
        this.size = size;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
