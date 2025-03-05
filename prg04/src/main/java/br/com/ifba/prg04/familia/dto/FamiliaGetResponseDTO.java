package br.com.ifba.prg04.familia.dto;

import lombok.Data;

import java.util.List;

@Data
public class FamiliaGetResponseDTO {

    private Long id;
    private String nome;
    private String endereco;
    private Long responsavelId;
    private List<String> membros;
}
