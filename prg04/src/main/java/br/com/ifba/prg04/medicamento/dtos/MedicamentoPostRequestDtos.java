package br.com.ifba.prg04.medicamento.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoPostRequestDtos {

    @NotBlank(message = "O nome do medicamento é obrigatório.")
    private String nome;

    @NotBlank(message = "A categoria do medicamento é obrigatória.")
    private String categoria;

    @NotBlank(message = "O fornecedor do medicamento é obrigatório.")
    private String fornecedor;

    @NotNull(message = "A data de fabricação é obrigatória. Digite nesse formato: yyyy-MM-dd")
    /*Declarando o formato da data*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past(message = "Data Precisa ser atual ou anterior")
    private Date dataDeFabricacao;

    @NotNull(message = "A data de validade é obrigatória. Digite nesse formato: yyyy-MM-dd")
    /*Precisa ser a data no futuro ou atual*/
    @Future(message = "A data deve ser atual ou futura")
    /*Declarando o formato da data*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataDeValidade;

    @Positive(message = "A quantidade deve ser um valor positivo.")
    private double quantidade;

    @Positive(message = "O número do lote deve ser um valor positivo.")
    private int lote;

    private String instrucaoArmazenamento;

    @NotNull(message = "O código de registro na Anvisa é obrigatório.")
    private Double registroAnvisa;

    @NotBlank(message = "O tipo de receita é obrigatório.")
    private String tipoReceita;

    private String descricao;

}
