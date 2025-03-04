package br.com.ifba.prg04.visitadomiciliar.repository;

import br.com.ifba.prg04.visitadomiciliar.entity.VisitaDomiciliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

// Repositório JPA para a entidade VisitaDomiciliar,
// permitindo operações de persistência no banco de dados
public interface VisitaDomiciliarRepository extends JpaRepository<VisitaDomiciliar, Long> {
}


