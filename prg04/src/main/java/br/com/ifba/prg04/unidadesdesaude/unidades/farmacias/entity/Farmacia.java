package br.com.ifba.prg04.unidadesdesaude.unidades.farmacias.entity;

import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "farmacias")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Farmacia extends UnidadesSaude {
    // Lista de medicamentos comuns disponíveis na farmácia
    @ElementCollection
    private List<String> medicamentosComuns;

    // Lista de medicamentos controlados disponíveis (tarja preta)
    @ElementCollection
    private List<String> medicamentosControlados;

    // Lista de medicamentos especiais disponíveis
    @ElementCollection
    private List<String> medicamentosEspeciais;

    // Mapa que relaciona o nome de cada medicamento com sua quantidade em estoque
    @ElementCollection
    @MapKeyColumn(name = "medicamento")
    @Column(name = "estoque")
    private Map<String, Integer> estoqueMedicamentosComuns;

    @ElementCollection
    @MapKeyColumn(name = "medicamento")
    @Column(name = "estoque")
    private Map<String, Integer> estoqueMedicamentosControlados;

    @ElementCollection
    @MapKeyColumn(name = "medicamento")
    @Column(name = "estoque")
    private Map<String, Integer> estoqueMedicamentosEspeciais;
}