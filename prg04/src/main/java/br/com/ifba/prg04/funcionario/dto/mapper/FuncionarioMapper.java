package br.com.ifba.prg04.funcionario.dto.mapper;
import org.springframework.stereotype.Component;

import br.com.ifba.prg04.funcionario.dto.FuncionarioUpdateDto;
import br.com.ifba.prg04.funcionario.entities.Funcionario;

@Component
public class FuncionarioMapper {

    public Funcionario toEntity(FuncionarioUpdateDto dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setTelefone(dto.getTelefone());
        return funcionario;
    }
}