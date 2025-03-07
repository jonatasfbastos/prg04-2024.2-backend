package br.com.ifba.prg04.ubs.mapper;

// Importações necessárias para o mapeamento
import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.endereco.entity.EnderecoId;
import br.com.ifba.prg04.ubs.dto.UBSDto;
import br.com.ifba.prg04.ubs.dto.UBSGetDto;
import br.com.ifba.prg04.ubs.dto.UBSPutDto;
import br.com.ifba.prg04.ubs.entity.UBS;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import org.springframework.stereotype.Component;

// Marca a classe como um componente Spring gerenciável
@Component
public class UBSMapper {

    // Converte um UBSDto em uma entidade UBS
    public UBS toEntity(UBSDto dto) {
        // Cria uma nova instância de UBS
        UBS ubs = new UBS();

        // Preenche os campos básicos da UBS a partir do DTO da unidade de saúde
        ubs.setNome(dto.getUnidadeSaudeSaveRequestDto().getNome());
        ubs.setTipo(dto.getUnidadeSaudeSaveRequestDto().getTipo());
        ubs.setTelefone(dto.getUnidadeSaudeSaveRequestDto().getTelefone());
        ubs.setHorarioFuncionamento(dto.getUnidadeSaudeSaveRequestDto().getHorarioFuncionamento());
        ubs.setCapacidadeAtendimento(dto.getUnidadeSaudeSaveRequestDto().getCapacidadeAtendimento());
        ubs.setStatus(dto.getUnidadeSaudeSaveRequestDto().getStatus());

        // Constrói e associa o endereço à UBS
        Endereco endereco = getEndereco(dto);
        ubs.setEndereco(endereco);

        // Retorna a entidade UBS preenchida
        return ubs;
    }

    public UBS toEntity(UBSPutDto dto){
        UBS ubs = new UBS();

        // Preenche os campos da ubs a partir do DTO da unidade de saúde
        ubs.setNome(dto.getUnidadeSaudePutResquestDto().getNome());
        ubs.setTipo(dto.getUnidadeSaudePutResquestDto().getTipo());
        ubs.setTelefone(dto.getUnidadeSaudePutResquestDto().getTelefone());
        ubs.setHorarioFuncionamento(dto.getUnidadeSaudePutResquestDto().getHorarioFuncionamento());
        ubs.setCapacidadeAtendimento(dto.getUnidadeSaudePutResquestDto().getCapacidadeAtendimento());
        ubs.setStatus(dto.getUnidadeSaudePutResquestDto().getStatus());

        return ubs;
    }

    // Método auxiliar privado para criar o objeto Endereco a partir do DTO
    private static Endereco getEndereco(UBSDto dto) {
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

    // Converte uma entidade UBS em um UBSGetDto para resposta
    public UBSGetDto toDto(UBS ubs) {
        // Cria um DTO de unidade de saúde com os dados básicos da UBS
        UnidadeSaudeGetResponseDto unidadeDto = new UnidadeSaudeGetResponseDto(
                ubs.getNome(),
                ubs.getTipo(),
                ubs.getEndereco(),
                ubs.getTelefone(),
                ubs.getHorarioFuncionamento(),
                ubs.getCapacidadeAtendimento(),
                ubs.getStatus()
        );

        // Retorna o DTO completo com informações específicas da UBS
        return new UBSGetDto(
                unidadeDto,
                ubs.getQuantidadeConsultorios(),
                ubs.getProgramasSaude()
        );
    }
}