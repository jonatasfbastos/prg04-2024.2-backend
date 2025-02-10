package br.com.ifba.prg04.unidadesdesaude.unidades.hospitais.repository;

import br.com.ifba.prg04.unidadesdesaude.unidades.hospitais.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
