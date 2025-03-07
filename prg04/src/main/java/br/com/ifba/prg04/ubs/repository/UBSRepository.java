package br.com.ifba.prg04.ubs.repository;

import br.com.ifba.prg04.ubs.entity.UBS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UBSRepository extends JpaRepository<UBS, Long> {
    Optional<UBS> findUBSByTelefone(String telefone);
}
