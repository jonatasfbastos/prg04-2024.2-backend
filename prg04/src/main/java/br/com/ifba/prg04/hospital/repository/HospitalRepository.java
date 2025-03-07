package br.com.ifba.prg04.hospital.repository;

import br.com.ifba.prg04.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findHospitalByTelefone(String telefone);
}
