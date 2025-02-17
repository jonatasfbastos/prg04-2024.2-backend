package br.com.ifba.prg04.familia.dto;

import lombok.Data;

import java.util.List;

@Data
public class FamiliaGetResponseDTO {

    private Long id;
    private String nome;
    private String endereco;
    private String responsavel;
    private List<String> membros;
}
