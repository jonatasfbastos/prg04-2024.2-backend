package br.com.ifba.prg04.unidadesdesaude.unidades.upas.entity;

import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import br.com.ifba.prg04.unidadesdesaude.unidades.upas.enums.PorteUPA;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "upas")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UPA extends UnidadesSaude {
    // Porte da UPA (I, II ou III), conforme Portaria Nº 10/2017
    @Enumerated(EnumType.STRING)
    private PorteUPA porte;
}