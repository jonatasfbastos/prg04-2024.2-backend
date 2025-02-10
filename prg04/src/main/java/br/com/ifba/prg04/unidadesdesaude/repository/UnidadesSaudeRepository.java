package br.com.ifba.prg04.unidadesdesaude.repository;

import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadesSaudeRepository extends JpaRepository<UnidadesSaude, Long> {
}
