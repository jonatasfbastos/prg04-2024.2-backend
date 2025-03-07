package br.com.ifba.prg04.hospital.mapper;

import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.endereco.entity.EnderecoId;
import br.com.ifba.prg04.farmacia.dto.FarmaciaPutDto;
import br.com.ifba.prg04.farmacia.entity.Farmacia;
import br.com.ifba.prg04.hospital.dto.HospitalDto;
import br.com.ifba.prg04.hospital.dto.HospitalGetDto;
import br.com.ifba.prg04.hospital.dto.HospitalPutDto;
import br.com.ifba.prg04.hospital.entity.Hospital;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import org.springframework.stereotype.Component;

@Component
public class HospitalMapper {
    // Converte um HospitalDto em uma entidade Hospital
    public Hospital toEntity(HospitalDto dto) {
        // Cria uma nova instância de Hospital
        Hospital Hospital = new Hospital();

        // Preenche os campos da hospital a partir do DTO da unidade de saúde
        Hospital.setNome(dto.getUnidadeSaudeSaveRequestDto().getNome());
        Hospital.setTipo(dto.getUnidadeSaudeSaveRequestDto().getTipo());
        Hospital.setTelefone(dto.getUnidadeSaudeSaveRequestDto().getTelefone());
        Hospital.setHorarioFuncionamento(dto.getUnidadeSaudeSaveRequestDto().getHorarioFuncionamento());
        Hospital.setCapacidadeAtendimento(dto.getUnidadeSaudeSaveRequestDto().getCapacidadeAtendimento());
        Hospital.setStatus(dto.getUnidadeSaudeSaveRequestDto().getStatus());

        // Constrói e associa o endereço à hospital
        Endereco endereco = getEndereco(dto);
        Hospital.setEndereco(endereco);

        // Retorna a entidade Hospital preenchida
        return Hospital;
    }

    public Hospital toEntity(HospitalPutDto dto){
        Hospital hospital = new Hospital();

        // Preenche os campos da hospital a partir do DTO da unidade de saúde
        hospital.setNome(dto.getUnidadeSaudePutResquestDto().getNome());
        hospital.setTipo(dto.getUnidadeSaudePutResquestDto().getTipo());
        hospital.setTelefone(dto.getUnidadeSaudePutResquestDto().getTelefone());
        hospital.setHorarioFuncionamento(dto.getUnidadeSaudePutResquestDto().getHorarioFuncionamento());
        hospital.setCapacidadeAtendimento(dto.getUnidadeSaudePutResquestDto().getCapacidadeAtendimento());
        hospital.setStatus(dto.getUnidadeSaudePutResquestDto().getStatus());

        return hospital;
    }

    // Método auxiliar privado para criar o objeto Endereco a partir do DTO
    private static Endereco getEndereco(HospitalDto dto) {
        // Cria o identificador composto do endereço
        EnderecoId enderecoId = new EnderecoId();
        enderecoId.setRua(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getId().getRua());
        enderecoId.setCep(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getId().getCep());
        // Converte o número de String para Integer
        enderecoId.setNumero(Integer.parseInt(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getId().getNumero()));

        // Cria e preenche o objeto Endereco
        Endereco endereco = new Endereco();
        endereco.setId(enderecoId);
        endereco.setCidade(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getCidade());
        endereco.setBairro(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getBairro());
        endereco.setComplemento(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getComplemento());
        endereco.setUf(dto.getUnidadeSaudeSaveRequestDto().getEndereco().getUf());

        // Retorna o endereço construído
        return endereco;
    }

    // Converte uma entidade Hospital em um HospitalGetDto para resposta
    public HospitalGetDto toDto(Hospital hospital) {
        // Cria um DTO de unidade de saúde com os dados básicos da hospital
        UnidadeSaudeGetResponseDto unidadeDto = new UnidadeSaudeGetResponseDto(
                hospital.getNome(),
                hospital.getTipo(),
                hospital.getEndereco(),
                hospital.getTelefone(),
                hospital.getHorarioFuncionamento(),
                hospital.getCapacidadeAtendimento(),
                hospital.getStatus()
        );

        // Retorna o DTO completo com informações adicionais específicas da hospital
        return new HospitalGetDto(
                unidadeDto, hospital.getLeitosUTI(), hospital.getLeitosEnfermaria(), hospital.getLeitosPrivativo(), hospital.getLeitosEmergencia(), hospital.getEspecialidadesAtendidas(), hospital.getServicosLaboratoriais(), hospital.getExamesDiagnosticoImagem(), hospital.getCentrosCirurgicos()
        );
    }
}
