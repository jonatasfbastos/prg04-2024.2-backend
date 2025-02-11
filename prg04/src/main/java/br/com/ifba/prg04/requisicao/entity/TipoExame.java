package br.com.ifba.prg04.requisicao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_exames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoExame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
}