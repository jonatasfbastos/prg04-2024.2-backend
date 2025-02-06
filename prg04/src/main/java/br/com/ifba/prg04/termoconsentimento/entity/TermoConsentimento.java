package br.com.ifba.prg04.termoconsentimento.entity;

import br.com.ifba.prg04.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_termo_consentimento")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class TermoConsentimento extends PersistenceEntity {

    //Tocar para o tipo usuário
    @Column(nullable = false, updatable = false)
    private String paciente;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraConsentimento;
    @Column(nullable = false, updatable = false, length = 500)
    private String conteudo;
    @Column(nullable = false, updatable = false)
    private String assinaturaPaciente;
    // Trocar para o tipo funcionario
    @Column(nullable = false, updatable = false)
    private String funcionario;

    // Adicionar um HashSet de consultas

}
