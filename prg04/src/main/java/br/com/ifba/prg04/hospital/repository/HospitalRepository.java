package br.com.ifba.prg04.hospital.repository;

import br.com.ifba.prg04.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
