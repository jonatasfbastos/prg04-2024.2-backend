package br.com.ifba.prg04.unidadesdesaude.dto;

// Importações necessárias para o DTO
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

// Gera automaticamente getters, setters, toString, equals e hashCode
@Data
// Cria um construtor com todos os campos
@AllArgsConstructor
// Cria um construtor vazio
@NoArgsConstructor
public class UnidadeSaudeSaveRequestDto {

    // Mapeia o campo JSON "nome" e aplica validações
    @JsonProperty("nome")
    @NotBlank(message = "O nome da unidade de saúde não pode estar vazio.")
    @Size(max = 255, message = "O nome da unidade de saúde deve ter no máximo 255 caracteres.")
    private String nome;

    // Mapeia o campo JSON "tipo" para o tipo da unidade de saúde (enum)
    @JsonProperty("tipo")
    @NotNull(message = "O tipo da unidade de saúde deve ser especificado.")
    private TiposUnidades tipo;

    // Mapeia o campo JSON "endereco" para o DTO do endereço, com validação aninhada
    @JsonProperty("endereco")
    @Valid
    @NotNull(message = "O endereço da unidade de saúde deve ser especificado.")
    private EnderecoDto endereco;

    // Mapeia o campo JSON "telefone" com validação de preenchimento
    @JsonProperty("telefone")
    @NotBlank(message = "O telefone da unidade de saúde não pode estar vazio.")
    //@Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}", message = "O telefone deve estar no formato '(XX) XXXXX-XXXX'.")
    private String telefone;

    // Mapeia o campo JSON "horario_funcionamento" com validação de preenchimento
    @JsonProperty("horario_funcionamento")
    @NotBlank(message = "O horário de funcionamento deve ser especificado.")
    private String horarioFuncionamento;

    // Mapeia o campo JSON "capacidade_atendimento" com validação de preenchimento
    @JsonProperty("capacidade_atendimento")
    @NotNull(message = "A capacidade de atendimento deve ser informada.")
    private Integer capacidadeAtendimento;

    // Mapeia o campo JSON "status" para o status da unidade (enum)
    @JsonProperty("status")
    @NotNull(message = "O status da unidade de saúde deve ser especificado.")
    private Status status;
}