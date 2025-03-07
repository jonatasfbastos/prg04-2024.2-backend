package br.com.ifba.prg04.farmacia.repository;

import br.com.ifba.prg04.farmacia.entity.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
    Optional<Farmacia> findFarmaciaByTelefone(String telefone);
}
