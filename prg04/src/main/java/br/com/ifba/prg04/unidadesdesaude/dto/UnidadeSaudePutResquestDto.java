package br.com.ifba.prg04.unidadesdesaude.dto;

import br.com.ifba.prg04.endereco.dto.EnderecoDto;
import br.com.ifba.prg04.unidadesdesaude.enums.Status;
import br.com.ifba.prg04.unidadesdesaude.enums.TiposUnidades;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeSaudePutResquestDto {
    @JsonProperty("nome")
    @NotBlank(message = "O nome da unidade de saúde não pode estar vazio.")
    @Size(max = 255, message = "O nome da unidade de saúde deve ter no máximo 255 caracteres.")
    private String nome;

    @JsonProperty("tipo")
    @NotNull(message = "O tipo da unidade de saúde deve ser especificado.")
    private TiposUnidades tipo;

    @JsonProperty("telefone")
    @NotBlank(message = "O telefone da unidade de saúde não pode estar vazio.")
    //@Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}", message = "O telefone deve estar no formato '(XX) XXXXX-XXXX'.")
    private String telefone;

    @JsonProperty("horario_funcionamento")
    @NotBlank(message = "O horário de funcionamento deve ser especificado.")
    private String horarioFuncionamento;

    @JsonProperty("capacidade_atendimento")
    @NotNull(message = "A capacidade de atendimento deve ser informada.")
    private Integer capacidadeAtendimento;

    @JsonProperty("status")
    @NotNull(message = "O status da unidade de saúde deve ser especificado.")
    private Status status;
}
