package br.com.ifba.prg04.carteiravacinaretroativa.service;

import br.com.ifba.prg04.carteiravacinaretroativa.entity.CarteiraVacinaRetroativa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarteiraVacinaRetroativaIService {
    CarteiraVacinaRetroativa save(CarteiraVacinaRetroativa carteiraRetroativa);
//    CarteiraVacinaRetroativa findByIdPaciente(Long idPaciente);
Page<CarteiraVacinaRetroativa> findByIdPaciente(Pageable pageable, Long idPaciente);
}
