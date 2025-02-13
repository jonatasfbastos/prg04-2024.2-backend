package br.com.ifba.prg04.anamnese.service;

import br.com.ifba.prg04.anamnese.entity.Anamnese;

public interface AnamneseIService {

    public Anamnese save(Anamnese anamnese, Long prontuarioId);

    //public abstract void update(Anamnese anamnese);
}
