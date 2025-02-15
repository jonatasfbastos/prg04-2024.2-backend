package br.com.ifba.prg04.gestaofuncionario.dto.mapper;
import org.springframework.stereotype.Component;

import br.com.ifba.prg04.gestaofuncionario.dto.FuncionarioUpdateDto;
import br.com.ifba.prg04.gestaofuncionario.entities.Funcionario;

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