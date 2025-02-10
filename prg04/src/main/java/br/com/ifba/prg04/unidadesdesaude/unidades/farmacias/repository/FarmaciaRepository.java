package br.com.ifba.prg04.unidadesdesaude.unidades.farmacias.repository;

import br.com.ifba.prg04.unidadesdesaude.unidades.farmacias.entity.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
}
