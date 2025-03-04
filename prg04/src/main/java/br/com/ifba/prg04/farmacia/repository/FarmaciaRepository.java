package br.com.ifba.prg04.farmacia.repository;

import br.com.ifba.prg04.farmacia.entity.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
}
