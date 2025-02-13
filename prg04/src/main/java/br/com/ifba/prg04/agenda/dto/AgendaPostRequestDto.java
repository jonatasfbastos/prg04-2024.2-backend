package br.com.ifba.prg04.agenda.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaPostRequestDto {

    //PostRequest de Agenda com validações

    @JsonProperty(value = "titulo")
    @NotNull(message = "O titulo é obrigatório")
    @NotBlank(message = "titulo vazio, insira um titulo!")
    private String titulo;

    @JsonProperty(value = "descricao")
    @NotNull(message = "O descricao é obrigatório")
    @NotBlank(message = "descricao vazio, insira um descricao!")
    private String descricao;

    @JsonProperty(value = "dataHoraInicio")
    @NotNull(message = "O dataHoraInicio é obrigatório")
    private LocalDateTime dataHoraInicio;

    @JsonProperty(value = "dataHoraFim")
    @NotNull(message = "O dataHoraFim é obrigatório")
    private LocalDateTime dataHoraFim;

    private boolean cancelado;

    @NotNull(message = "usuario é obrigatório")
    private String nomeUsuario;

}
