package br.com.ifba.prg04.termoconsentimento.repository.projection;

import br.com.ifba.prg04.paciente.repository.projection.PacienteProjection;

import java.time.LocalDateTime;

public interface TermoConsentimentoProjection {
    Long getId();
    PacienteProjection getPaciente();
    LocalDateTime getDataHoraConsentimento();
}
