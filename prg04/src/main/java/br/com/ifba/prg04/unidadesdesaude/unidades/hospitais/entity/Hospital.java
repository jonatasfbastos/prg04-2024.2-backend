package br.com.ifba.prg04.unidadesdesaude.unidades.hospitais.entity;

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
@Table(name = "hospitais")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Hospital extends UnidadesSaude {
    // Quantidade de leitos disponíveis por tipo
    private Integer leitosUTI;
    private Integer leitosEnfermaria;
    private Integer leitosPrivativo;
    private Integer leitosEmergencia;

    // Lista de especialidades médicas atendidas pelo hospital
    @ElementCollection
    private List<String> especialidadesAtendidas;

    // Lista de Serviços Laboratoriais do hospital
    @ElementCollection
    private List<String> servicosLaboratoriais;

    // Lista de exames de imagem disponíveis no hospital (ex: raio-X, tomografia, ultrassom)
    @ElementCollection
    private List<String> examesDiagnosticoImagem;

    // Lista de centros cirúrgicos disponíveis (quantidade e tipos de procedimentos)
    @ElementCollection
    private List<String> centrosCirurgicos;
}

