package br.com.ifba.prg04.unidadesdesaude.unidades.farmacias.entity;

import br.com.ifba.prg04.medicamento.entity.Medicamento;
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
    @ManyToMany
    private List<Medicamento> medicamentosComuns;

    // Lista de medicamentos controlados disponíveis (tarja preta)
    @ManyToMany
    private List<Medicamento> medicamentosControlados;

    // Lista de medicamentos especiais disponíveis
    @ManyToMany
    private List<Medicamento> medicamentosEspeciais;
}