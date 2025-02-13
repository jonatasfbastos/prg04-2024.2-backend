package br.com.ifba.prg04.GestaoFuncionario.dto.mapper;
import br.com.ifba.prg04.GestaoFuncionario.dto.FuncionarioUpdateDto;
import br.com.ifba.prg04.GestaoFuncionario.entities.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {

    public Funcionario toEntity(FuncionarioUpdateDto dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setSenha(dto.getSenha());
        funcionario.setEndereco(dto.getEndereco());
        funcionario.setTelefone(dto.getTelefone());
        return funcionario;
    }
}