package br.com.ifba.prg04.unidadesdesaude.unidades.ubs.entity;

import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ubs")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UBS extends UnidadesSaude {
    // Número total de consultórios disponíveis na UBS
    private Integer quantidadeConsultorios;

    // Lista de programas de saúde oferecidos (ex: vacinação, pré-natal, etc.)
    @ElementCollection
    private List<String> programasSaude;
}
