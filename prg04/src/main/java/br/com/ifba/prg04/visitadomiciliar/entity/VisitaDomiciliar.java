package br.com.ifba.prg04.visitadomiciliar.entity;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "visitas_domiciliares")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitaDomiciliar extends PersistenceEntity {

    private String digitadoPor;
    private LocalDate data;
    private String conferidoPor;
    private String numeroFolha;
    private String cns;
    private String cbo;
    private String cnes;
    private String ine;
}

