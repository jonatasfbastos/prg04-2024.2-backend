package br.com.ifba.prg04.prontuario.service;

import br.com.ifba.prg04.prontuario.entities.Anamnese;
import br.com.ifba.prg04.prontuario.entities.Paciente;
import br.com.ifba.prg04.prontuario.entities.Prontuario;

public interface ProntuarioIService {

    public abstract void save(Prontuario prontuario);

    public abstract void savePaciente(Paciente paciente);

    public abstract void saveAnamnese(Paciente paciente , Anamnese anamnese);

    public abstract void update(Paciente paciente);

    public abstract Prontuario findByPaciente(Prontuario prontuario);

}
