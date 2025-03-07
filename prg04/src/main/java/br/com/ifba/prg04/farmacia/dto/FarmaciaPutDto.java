package br.com.ifba.prg04.farmacia.dto;

import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudePutResquestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmaciaPutDto {

    @JsonProperty("unidade")
    @Valid
    private UnidadeSaudePutResquestDto unidadeSaudePutResquestDto;
}
