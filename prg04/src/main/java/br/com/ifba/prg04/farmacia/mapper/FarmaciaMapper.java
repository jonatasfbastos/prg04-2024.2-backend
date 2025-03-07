package br.com.ifba.prg04.farmacia.mapper;

// Importações necessárias para o mapeamento
import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.endereco.entity.EnderecoId;
import br.com.ifba.prg04.farmacia.dto.FarmaciaDto;
import br.com.ifba.prg04.farmacia.dto.FarmaciaGetDto;
import br.com.ifba.prg04.farmacia.dto.FarmaciaPutDto;
import br.com.ifba.prg04.farmacia.entity.Farmacia;
import br.com.ifba.prg04.unidadesdesaude.dto.UnidadeSaudeGetResponseDto;
import org.springframework.stereotype.Component;

// Marca a classe como um componente Spring gerenciável
@Component
public class FarmaciaMapper {

    // Converte um FarmaciaDto em uma entidade Farmacia
    public Farmacia toEntity(FarmaciaDto dto) {
        // Cria uma nova instância de Farmacia
        Farmacia farmacia = new Farmacia();

        // Preenche os campos da farmácia a partir do DTO da unidade de saúde
        farmacia.setNome(dto.getUnidadeSaudeSaveRequestDto().getNome());
        farmacia.setTipo(dto.getUnidadeSaudeSaveRequestDto().getTipo());
        farmacia.setTelefone(dto.getUnidadeSaudeSaveRequestDto().getTelefone());
        farmacia.setHorarioFuncionamento(dto.getUnidadeSaudeSaveRequestDto().getHorarioFuncionamento());
        farmacia.setCapacidadeAtendimento(dto.getUnidadeSaudeSaveRequestDto().getCapacidadeAtendimento());
        farmacia.setStatus(dto.getUnidadeSaudeSaveRequestDto().getStatus());

        // Constrói e associa o endereço à farmácia
        Endereco endereco = getEndereco(dto);
        farmacia.setEndereco(endereco);

        // Retorna a entidade Farmacia preenchida
        return farmacia;
    }

    public Farmacia toEntity(FarmaciaPutDto dto){
        Farmacia farmacia = new Farmacia();

        // Preenche os campos da farmácia a partir do DTO da unidade de saúde
        farmacia.setNome(dto.getUnidadeSaudePutResquestDto().getNome());
        farmacia.setTipo(dto.getUnidadeSaudePutResquestDto().getTipo());
        farmacia.setTelefone(dto.getUnidadeSaudePutResquestDto().getTelefone());
        farmacia.setHorarioFuncionamento(dto.getUnidadeSaudePutResquestDto().getHorarioFuncionamento());
        farmacia.setCapacidadeAtendimento(dto.getUnidadeSaudePutResquestDto().getCapacidadeAtendimento());
        farmacia.setStatus(dto.getUnidadeSaudePutResquestDto().getStatus());

        return farmacia;
    }

    // Método auxiliar privado para criar o objeto Endereco a partir do DTO
    private static Endereco getEndereco(FarmaciaDto dto) {
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

    // Converte uma entidade Farmacia em um FarmaciaGetDto para resposta
    public FarmaciaGetDto toDto(Farmacia farmacia) {
        // Cria um DTO de unidade de saúde com os dados básicos da farmácia
        UnidadeSaudeGetResponseDto unidadeDto = new UnidadeSaudeGetResponseDto(
                farmacia.getNome(),
                farmacia.getTipo(),
                farmacia.getEndereco(),
                farmacia.getTelefone(),
                farmacia.getHorarioFuncionamento(),
                farmacia.getCapacidadeAtendimento(),
                farmacia.getStatus()
        );

        // Retorna o DTO completo com informações adicionais específicas da farmácia
        return new FarmaciaGetDto(
                unidadeDto,
                farmacia.getMedicamentosComuns(),
                farmacia.getMedicamentosControlados(),
                farmacia.getMedicamentosEspeciais()
        );
    }
}