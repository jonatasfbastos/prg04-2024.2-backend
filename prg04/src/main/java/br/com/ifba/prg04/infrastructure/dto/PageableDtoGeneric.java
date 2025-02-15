package br.com.ifba.prg04.infrastructure.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableDtoGeneric<T> {
   private List<T> content = new ArrayList<>();
    private boolean first;
    private boolean last;
    @JsonProperty("page")
    private int number;
    private int size;
    @JsonProperty("pageElements")
    private int numberOfElements;
    private int totalPages;
    private int totalElements;

    // Construtor para facilitar a criação do PageableDto
    public PageableDtoGeneric(List<T> content, int number, int size, int numberOfElements, int totalPages, int totalElements, boolean first, boolean last) {
        this.content = content;
        this.number = number;
        this.size = size;
        this.numberOfElements = numberOfElements;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.last = last;
    }

    // Método que cria o PageableDto a partir de um Page
    public static <T> PageableDtoGeneric<T> fromPage(org.springframework.data.domain.Page<T> page) {
        return new PageableDtoGeneric<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getNumberOfElements(),
                page.getTotalPages(),
                (int) page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }
}
