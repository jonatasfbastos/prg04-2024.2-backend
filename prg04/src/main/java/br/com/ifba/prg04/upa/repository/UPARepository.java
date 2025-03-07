package br.com.ifba.prg04.upa.repository;

import br.com.ifba.prg04.upa.entity.UPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UPARepository extends JpaRepository<UPA, Long> {
    Optional<UPA> findUPAByTelefone(String telefone);
}
