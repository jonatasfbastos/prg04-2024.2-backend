package br.com.ifba.prg04.paciente.service;

import br.com.ifba.prg04.paciente.entity.Paciente;

public interface PacienteIService {

    public abstract Paciente save(Paciente paciente);

    public abstract void update(Paciente paciente);

   public abstract Paciente findByCpf(String cpf);

   public abstract Paciente findById(Long id);
}
