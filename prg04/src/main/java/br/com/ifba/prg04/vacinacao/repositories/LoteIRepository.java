package br.com.ifba.prg04.vacinacao.repositories;

import br.com.ifba.prg04.vacinacao.entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteIRepository extends JpaRepository<Lote, Long> {

}
