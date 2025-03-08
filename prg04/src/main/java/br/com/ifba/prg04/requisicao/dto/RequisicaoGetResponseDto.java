package br.com.ifba.prg04.requisicao.dto;

import br.com.ifba.prg04.paciente.dto.PacienteGetResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data // Adiciona getters, setters, toString, equals e hashCode
@NoArgsConstructor // Construtor sem argumentos
@AllArgsConstructor // Construtor com todos os argumentos
public class RequisicaoGetResponseDto {
    private Long id;
    private LocalDateTime dataRequisicao;
    private PacienteGetResponseDto paciente;
    private List<String> exames; // Já está como List<String>, compatível com o serviço
}