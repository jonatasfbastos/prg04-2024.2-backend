package br.com.ifba.prg04.agenda.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendaGetResponseDto {

    //GetResponse de Agenda

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "titulo")
    private String titulo;

    @JsonProperty(value = "descricao")
    private String descricao;

    @JsonProperty(value = "dataHoraInicio")
    private LocalDateTime dataHoraInicio;

    @JsonProperty(value = "dataHoraFim")
    private LocalDateTime dataHoraFim;

    @JsonProperty(value = "cancelado")
    private boolean cancelado;

    private String nomeUsuario;

}
