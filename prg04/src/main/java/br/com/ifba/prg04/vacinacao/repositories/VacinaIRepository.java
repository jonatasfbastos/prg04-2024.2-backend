package br.com.ifba.prg04.vacinacao.repositories;

import br.com.ifba.prg04.vacinacao.entities.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaIRepository extends JpaRepository<Vacina, Long> {
}
