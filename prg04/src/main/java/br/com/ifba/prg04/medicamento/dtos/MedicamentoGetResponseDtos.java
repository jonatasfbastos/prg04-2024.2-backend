package br.com.ifba.prg04.medicamento.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/*Arquivo enviado para o front-end*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoGetResponseDtos {

    private Long id;
    private String nome;
    private String categoria;
    private String fornecedor;
    private Date dataDeFabricacao;
    private Date dataDeValidade;
    private double quantidade;
    private int lote;
    private String instrucaoArmazenamento;
    private Double registroAnvisa;
    private String tipoReceita;
    private String descricao;

}
