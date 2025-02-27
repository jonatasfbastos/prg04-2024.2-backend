package br.com.ifba.prg04.gestaoatendimento.mapper;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.ifba.prg04.gestaoatendimento.dto.DtoAtendimentoResponse;
import br.com.ifba.prg04.gestaoatendimento.entity.GestaoAtendimento;
import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.usuario.dto.UsuarioResponseDto;
import java.util.List;

public class GestaoAtendimentoMapper {
     // Método estático para mapear GestaoAtendimento para DtoAtendimentoResponse
    public static DtoAtendimentoResponse toDtoAtendimentoResponse(GestaoAtendimento gestaoAtendimento) {
        

        // Mapeando o GestaoAtendimento para DtoAtendimentoResponse
        return new DtoAtendimentoResponse(
            gestaoAtendimento.getCodigo(),
            gestaoAtendimento.getDataHora(),
            gestaoAtendimento.getEspecialidadeMedica(),
            gestaoAtendimento.getPaciente()
        );
    }

    // Método estático para mapear uma página de GestaoAtendimento para uma página de DtoAtendimentoResponse
    public static Page<DtoAtendimentoResponse> toDtoAtendimentoResponsePage(Page<GestaoAtendimento> gestaoAtendimentosPage) {
        return gestaoAtendimentosPage.map(gestaoAtendimento -> toDtoAtendimentoResponse(gestaoAtendimento));
    }

      // Método estático para mapear uma lista de GestaoAtendimento para uma lista de DtoAtendimentoResponse
    public static List<DtoAtendimentoResponse> toDtoAtendimentoResponseList(List<GestaoAtendimento> gestaoAtendimentos) {
        return gestaoAtendimentos.stream()
            .map(GestaoAtendimentoMapper::toDtoAtendimentoResponse) // Usando o método para mapear um GestaoAtendimento
            .collect(Collectors.toList());
    }

}
