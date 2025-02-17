package br.com.ifba.prg04.requisicao.dto;

import br.com.ifba.prg04.paciente.dto.PacienteGetResponseDto;
import java.time.LocalDateTime;
import java.util.List;

public class RequisicaoGetResponseDto {
    private Long id;
    private LocalDateTime dataRequisicao;
    private PacienteGetResponseDto paciente;
    private List<String> exames; // Agora é uma lista de strings

    public RequisicaoGetResponseDto(Long id, LocalDateTime dataRequisicao, PacienteGetResponseDto paciente, List<String> exames) {
        this.id = id;
        this.dataRequisicao = dataRequisicao;
        this.paciente = paciente;
        this.exames = exames;
    }

    // Getters e Setters (ou use Lombok @Data se preferir)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(LocalDateTime dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public PacienteGetResponseDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteGetResponseDto paciente) {
        this.paciente = paciente;
    }

    public List<String> getExames() {
        return exames;
    }

    public void setExames(List<String> exames) {
        this.exames = exames;
    }
}