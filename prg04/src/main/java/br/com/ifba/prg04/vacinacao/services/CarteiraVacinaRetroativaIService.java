package br.com.ifba.prg04.vacinacao.services;

import br.com.ifba.prg04.vacinacao.entities.CarteiraVacinaRetroativa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarteiraVacinaRetroativaIService {
    CarteiraVacinaRetroativa save(CarteiraVacinaRetroativa carteiraRetroativa);
//    CarteiraVacinaRetroativa findByIdPaciente(Long idPaciente);
Page<CarteiraVacinaRetroativa> findByIdPaciente(Pageable pageable, Long idPaciente);
}
