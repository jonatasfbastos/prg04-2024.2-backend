package br.com.ifba.prg04.medicamento.entity;


import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento extends PersistenceEntity{

    /*Nome do Medicamento*/
    private String nome;

    /*Categoria do medicamento*/
    private String categoria;

    /*Fabricante*/
    private String fornecedor;

    /*data de fabricação*/
    private Date dataDeFabricacao;

    /*data de validade do medicamento*/
    private Date dataDeValidade;

    /*Quantidade no estoque*/
    private double quantidade;

    /*Qual lote está armazenados*/
    private int lote;

    /*Instrução de armzaenamento do medicamento*/
    private String instrucaoArmazenamento;

    /*Código no registro do orgão compentente*/
    private Double registroAnvisa;

    /*Qual tipo de receita, grave, médio, leve*/
    private String tipoReceita;

    /*Descrição do remédio, efeitos colaterais etc...*/
    private String descricao;

}
