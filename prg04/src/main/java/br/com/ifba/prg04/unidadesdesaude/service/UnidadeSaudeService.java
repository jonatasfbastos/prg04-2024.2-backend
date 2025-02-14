package br.com.ifba.prg04.unidadesdesaude.service;

import br.com.ifba.prg04.endereco.entity.EnderecoId;
import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import br.com.ifba.prg04.unidadesdesaude.repository.UnidadesSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnidadeSaudeService implements UnidadeSaudeIService{

    private final UnidadesSaudeRepository unidadesSaudeRepository;

    @Override
    public Page<UnidadesSaude> findAll(Pageable pageable) {
        return unidadesSaudeRepository.findAll(pageable);
    }

    @Override
    public UnidadesSaude findById(Long id) {
        return unidadesSaudeRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Unidade de id " + id + " não encontrado"));
    }

    @Transactional
    @Override
    public UnidadesSaude save(UnidadesSaude unidadesSaude) {
        return unidadesSaudeRepository.save(unidadesSaude);
    }

    @Transactional
    @Override
    public void delete(String telefone) {
        UnidadesSaude unidadeDelete = unidadesSaudeRepository.findByTelefone(telefone).orElseThrow(() -> new BusinessException("Unidade de telefone " + telefone + " não encontrado"));
        unidadesSaudeRepository.deleteById(unidadeDelete.getId());
    }

    @Transactional
    @Override
    public UnidadesSaude update(UnidadesSaude unidadesSaude) {
        UnidadesSaude unidadesSaudeAtual = unidadesSaudeRepository.findByTelefone(unidadesSaude.getTelefone()).orElseThrow(() -> new BusinessException("Unidade de telefone " + unidadesSaude.getTelefone() + " não encontrado"));
        if (unidadesSaudeAtual != null) {
//            unidadesSaudeAtual.setNome(unidadesSaude.getNome());
//            unidadesSaudeAtual.setTelefone(unidadesSaude.getTelefone());
//            unidadesSaudeAtual.setTipo(unidadesSaude.getTipo());
//            unidadesSaudeAtual.setStatus(unidadesSaude.getStatus());
//            unidadesSaudeAtual.setCapacidadeAtendimento(unidadesSaude.getCapacidadeAtendimento());
//            unidadesSaudeAtual.setHorarioFuncionamento(unidadesSaude.getHorarioFuncionamento());
            unidadesSaude.setId(unidadesSaudeAtual.getId());
            unidadesSaude.setEndereco(unidadesSaudeAtual.getEndereco());
            return unidadesSaudeRepository.save(unidadesSaude);
        }
        // Lança uma exceção caso o usuário não seja encontrado
        throw new BusinessException("Unidade de Saúde com telefone: " + unidadesSaude.getTelefone() + " não encontrado");
    }

    @Override
    public UnidadesSaude findByNome(String nome) {
        return unidadesSaudeRepository.findByNome(nome).orElseThrow(() -> new BusinessException("Unidade de nome " + nome + " não encontrado"));
    }
}
