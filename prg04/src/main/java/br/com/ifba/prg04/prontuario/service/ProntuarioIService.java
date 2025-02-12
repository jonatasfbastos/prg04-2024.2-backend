package br.com.ifba.prg04.prontuario.service;

import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.prontuario.entity.Prontuario;

public interface ProntuarioIService {

    public abstract void save(Prontuario prontuario);

    public abstract void update(Paciente paciente);


}
