package br.com.ifba.prg04.termoconsentimento.repository.projection;

import java.time.LocalDateTime;

public interface TermoConsentimentoProjection {

    /* Trocar o tipo de dado dos atributos paciente e funcionario
        para um projection de cada classe.
        Talvez adicionar getConcultas com um projection de Consulta
     */

    Long getId();
    String getPaciente();
    LocalDateTime getDataHoraConsentimento();
    String getConteudo();
    String getFuncionario();

}
