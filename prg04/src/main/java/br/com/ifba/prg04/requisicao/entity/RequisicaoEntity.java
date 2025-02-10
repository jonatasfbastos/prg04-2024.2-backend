package br.com.ifba.prg04.requisicao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "requisicoes_exames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataRequisicao;

//    @ManyToOne
//    @JoinColumn(name = "medico_id", nullable = false)
//    private Medico medico;
//
//    @ManyToOne
//    @JoinColumn(name = "paciente_id", nullable = false)
//    private Paciente paciente;
//
//    @ManyToOne
//    @JoinColumn(name = "tipo_exame_id", nullable = false)
//    private TipoExame tipoExame;
}