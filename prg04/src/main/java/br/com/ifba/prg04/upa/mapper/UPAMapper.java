package br.com.ifba.prg04.upa.mapper;

import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.endereco.entity.EnderecoId;
import br.com.ifba.prg04.upa.dto.UPADto;
import br.com.ifba.prg04.upa.dto.UPAGetDto;
import br.com.ifba.prg04.upa.dto.UPAPutDto;
import br.com.ifba.prg04.upa.entity.UPA;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UPAMapper{
    public UPA toEntity(UPADto dto) {
        UPA upa = new UPA();

        upa.setNome(dto.getUnidadeSaudeSaveRequestDto().getNome());
        upa.setTipo(dto.getUnidadeSaudeSaveRequestDto().getTipo());
        upa.setTelefone(dto.getUnidadeSaudeSaveRequestDto().getTelefone());
        upa.setHorarioFuncionamento(dto.getUnidadeSaudeSaveRequestDto().getHorarioFuncionamento());
        upa.setCapacidadeAtendimento(dto.getUnidadeSaudeSaveRequestDto().getCapacidadeAtendimento());
        upa.setStatus(dto.getUnidadeSaudeSaveRequestDto().getStatus());

        Endereco endereco = getEndereco(dto);
        upa.setEndereco(endereco);

        return upa;
    }

    public UPA toEntity(UPAPutDto dto){
        UPA upa = new UPA();

        // Preenche os campos da upa a partir do DTO da unidade de saúde
        upa.setNome(dto.getUnidadeSaudePutResquestDto().getNome());
        upa.setTipo(dto.getUnidadeSaudePutResquestDto().getTipo());
        upa.setTelefone(dto.getUnidadeSaudePutResquestDto().getTelefone());
        upa.setHorarioFuncionamento(dto.getUnidadeSaudePutResquestDto().getHorarioFuncionamento());
        upa.setCapacidadeAtendimento(dto.getUnidadeSaudePutResquestDto().getCapacidadeAtendimento());
        upa.setStatus(dto.getUnidadeSaudePutResquestDto().getStatus());

        return upa;
    }

    private static Endereco getEndereco(UPADto dto) {
        EnderecoId enderecoId = new EnderecoId();
        enderecoId.setRua(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getId().getRua());
        enderecoId.setCep(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getId().getCep());
        enderecoId.setNumero(Integer.parseInt(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getId().getNumero()));

        Endereco endereco = new Endereco();
        endereco.setId(enderecoId);
        endereco.setCidade(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getCidade());
        endereco.setBairro(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getBairro());
        endereco.setComplemento(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getComplemento());
        endereco.setUf(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getUf());

        return endereco;
    }

    public UPAGetDto toDto(UPA upa) {
        UnidadeSaudeGetResponseDto unidadeDto = new UnidadeSaudeGetResponseDto(
                upa.getNome(),
                upa.getTipo(),
                upa.getEndereco(),
                upa.getTelefone(),
                upa.getHorarioFuncionamento(),
                upa.getCapacidadeAtendimento(),
                upa.getStatus()
        );

        return new UPAGetDto(
                unidadeDto,
                upa.getPorte()
        );
    }
}
