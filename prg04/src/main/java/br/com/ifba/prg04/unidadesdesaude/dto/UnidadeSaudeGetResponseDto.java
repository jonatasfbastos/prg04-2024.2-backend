package br.com.ifba.prg04.unidadesdesaude.dto;

// Importações necessárias para o DTO
import br.com.ifba.prg04.endereco.dto.EnderecoDto; // Não utilizada diretamente neste DTO
import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.unidadesdesaude.enums.Status;
import br.com.ifba.prg04.unidadesdesaude.enums.TiposUnidades;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Gera automaticamente getters, setters, toString, equals e hashCode
@Data
// Cria um construtor com todos os campos
@AllArgsConstructor
// Cria um construtor vazio
@NoArgsConstructor
public class UnidadeSaudeGetResponseDto {
    // Mapeia o campo JSON "nome" para o nome da unidade de saúde
    @JsonProperty("nome")
    private String nome;

    // Mapeia o campo JSON "tipo" para o tipo da unidade de saúde (enum)
    @JsonProperty("tipo")
    private TiposUnidades tipo;

    // Mapeia o campo JSON "endereco" para o objeto de endereço da unidade
    @JsonProperty("endereco")
    private Endereco endereco;

    // Mapeia o campo JSON "telefone" para o telefone da unidade de saúde
    @JsonProperty("telefone")
    private String telefone;

    // Mapeia o campo JSON "horario_funcionamento" para o horário de funcionamento
    @JsonProperty("horario_funcionamento")
    private String horarioFuncionamento;

    // Mapeia o campo JSON "capacidade_atendimento" para a capacidade de atendimento
    @JsonProperty("capacidade_atendimento")
    private Integer capacidadeAtendimento;

    // Mapeia o campo JSON "status" para o status da unidade (enum)
    @JsonProperty("status")
    private Status status;
}