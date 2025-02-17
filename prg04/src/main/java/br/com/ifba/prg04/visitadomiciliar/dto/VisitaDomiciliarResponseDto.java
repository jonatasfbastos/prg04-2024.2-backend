package br.com.ifba.prg04.visitadomiciliar.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitaDomiciliarResponseDto {
    private Long id;
    private String digitadoPor;
    private LocalDate data;
    private String conferidoPor;
    private String numeroFolha;
    private String cns;
    private String cbo;
    private String cnes;
    private String ine;
    private String motivoVisita;
    private String acompanhamento;
    private String controleAmbiental;
    private String antropometria;
    private String sinaisVitais;
    private String glicemia;
    private String desfecho;
}



