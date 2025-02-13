package br.com.ifba.prg04.agenda.service;

import br.com.ifba.prg04.agenda.entity.Agenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//interface service de Agenda
public interface AgendaIService {

    Page<Agenda> findAll(Pageable pageable);

    Agenda save(Agenda agenda);

    Agenda findById(Long id);

    void delete(Long id);

    Agenda update(Long id, Agenda agenda);

    Agenda cancelar(Long id);

}
