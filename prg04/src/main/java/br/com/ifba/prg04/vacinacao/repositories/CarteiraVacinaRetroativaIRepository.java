package br.com.ifba.prg04.vacinacao.repositories;

import br.com.ifba.prg04.vacinacao.entities.CarteiraVacinaRetroativa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraVacinaRetroativaIRepository extends JpaRepository<CarteiraVacinaRetroativa, Long> {
    Page<CarteiraVacinaRetroativa> findByPaciente_Id(Long pacienteId, Pageable pageable);

//    Page<CarteiraVacinaRetroativa> findByPaciente_Id(Long pacienteId);

//    Long id(Long id);
}
