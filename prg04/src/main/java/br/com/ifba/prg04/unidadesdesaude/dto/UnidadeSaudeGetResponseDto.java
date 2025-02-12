package br.com.ifba.prg04.unidadesdesaude.dto;

import br.com.ifba.prg04.endereco.dto.EnderecoDto;
import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.unidadesdesaude.enums.Status;
import br.com.ifba.prg04.unidadesdesaude.enums.TiposUnidades;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeSaudeGetResponseDto {
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("tipo")
    private TiposUnidades tipo;

    @JsonProperty("endereco")
    private Endereco endereco;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("horario_funcionamento")
    private String horarioFuncionamento;

    @JsonProperty("capacidade_atendimento")
    private Integer capacidadeAtendimento;

    @JsonProperty("status")
    private Status status;
}
