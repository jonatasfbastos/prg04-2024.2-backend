package br.com.ifba.prg04.anamnese.repository;

import br.com.ifba.prg04.anamnese.entity.Anamnese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {

}
