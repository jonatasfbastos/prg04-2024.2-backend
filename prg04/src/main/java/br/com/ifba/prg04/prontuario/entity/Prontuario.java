package br.com.ifba.prg04.prontuario.entity;

import br.com.ifba.prg04.anamnese.entity.Anamnese;
import br.com.ifba.prg04.documento.entity.Documento;
import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import br.com.ifba.prg04.paciente.entity.Paciente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Prontuario extends PersistenceEntity {

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;


    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL)
    private List<Anamnese> anamneses;

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL)
    private List<Documento> documentos;

}
