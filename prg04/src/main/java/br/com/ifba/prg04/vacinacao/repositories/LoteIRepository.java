package br.com.ifba.prg04.vacinacao.repositories;

import br.com.ifba.prg04.vacinacao.entities.Lote;
import br.com.ifba.prg04.vacinacao.entities.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoteIRepository extends JpaRepository<Lote, Long> {

}
