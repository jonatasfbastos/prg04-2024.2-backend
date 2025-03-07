package br.com.ifba.prg04.hospital.dto;

import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudePutResquestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalPutDto {

    @JsonProperty("unidade")
    @Valid
    private UnidadeSaudePutResquestDto unidadeSaudePutResquestDto;
}
