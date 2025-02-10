package br.com.ifba.prg04.paciente.service;

import br.com.ifba.prg04.paciente.entity.Paciente;

public interface PacienteIService {

    Paciente findByCpf(String cpf);
    Paciente findById(Long id);

}
