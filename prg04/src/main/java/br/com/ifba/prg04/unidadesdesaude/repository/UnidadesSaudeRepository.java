package br.com.ifba.prg04.unidadesdesaude.repository;

import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnidadesSaudeRepository extends JpaRepository<UnidadesSaude, Long> {

    Optional<UnidadesSaude> findByNome(String nome);
}
