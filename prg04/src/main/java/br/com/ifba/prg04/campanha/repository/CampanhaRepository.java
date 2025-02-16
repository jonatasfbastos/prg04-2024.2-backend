package br.com.ifba.prg04.campanha.repository;

import br.com.ifba.prg04.campanha.entity.Campanha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    Page<Campanha> findByVacina(String vacina, Pageable pageable);
}
