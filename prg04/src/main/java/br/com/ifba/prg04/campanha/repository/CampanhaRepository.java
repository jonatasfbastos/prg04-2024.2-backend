package br.com.ifba.prg04.campanha.repository;

import br.com.ifba.prg04.campanha.entity.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
}
