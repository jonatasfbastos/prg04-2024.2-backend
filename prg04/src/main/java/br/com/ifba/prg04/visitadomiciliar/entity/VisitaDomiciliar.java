package br.com.ifba.prg04.visitadomiciliar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import br.com.ifba.prg04.funcionario.entities.Funcionario;

@Entity
@Table(name = "visitas_domiciliares")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VisitaDomiciliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String numeroFolha;

    @Column(nullable = false)
    private String cns;

    @Column(nullable = false)
    private String cbo;

    @Column(nullable = false)
    private String cnes;

    @Column(nullable = false)
    private String ine;

    @Column(nullable = false)
    private String motivoVisita;

    @Column(nullable = false)
    private String acompanhamento;

    @Column(nullable = false)
    private String controleAmbiental;

    @Column(nullable = false)
    private String antropometria;

    @Column(nullable = false)
    private String sinaisVitais;

    @Column(nullable = false)
    private String glicemia;

    @Column(nullable = false)
    private String desfecho;

    @ManyToOne
    @JoinColumn(name = "digitado_por_id", referencedColumnName = "id", nullable = false)
    private Funcionario digitadoPor;

    @ManyToOne
    @JoinColumn(name = "conferido_por_id", referencedColumnName = "id", nullable = false)
    private Funcionario conferidoPor;
}




