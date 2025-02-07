package br.com.ifba.prg04.prontuario.entities;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Prontuario extends PersistenceEntity {

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL)
    private List<Anamnese> anamneses;

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL)
    private List<Documento> documentos;

    private LocalDateTime dataCriacao = LocalDateTime.now();
}
