package br.com.ifba.prg04.documento.service;

import br.com.ifba.prg04.anamnese.entity.Anamnese;
import br.com.ifba.prg04.documento.entity.Documento;

public interface DocumentoIService {

    public Documento save(Documento documento, Long prontuarioId);
}
