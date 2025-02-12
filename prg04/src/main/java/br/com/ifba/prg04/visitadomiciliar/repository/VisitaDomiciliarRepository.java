package br.com.ifba.prg04.visitadomiciliar.repository;

import br.com.ifba.prg04.visitadomiciliar.entity.VisitaDomiciliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaDomiciliarRepository extends JpaRepository<VisitaDomiciliar, Long> {
}


