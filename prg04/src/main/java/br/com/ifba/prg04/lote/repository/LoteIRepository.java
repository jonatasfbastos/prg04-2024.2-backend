package br.com.ifba.prg04.lote.repository;

import br.com.ifba.prg04.lote.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteIRepository extends JpaRepository<Lote, Long> {
}
