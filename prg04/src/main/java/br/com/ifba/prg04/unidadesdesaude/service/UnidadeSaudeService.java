package br.com.ifba.prg04.unidadesdesaude.service;

// Importações necessárias para o serviço
import br.com.ifba.prg04.endereco.entity.EnderecoId; // Não utilizada diretamente neste código
import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.unidadesdesaude.entity.UnidadesSaude;
import br.com.ifba.prg04.unidadesdesaude.repository.UnidadesSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Marca a classe como um serviço gerenciado pelo Spring
@Service
// Gera automaticamente um construtor com os campos finais requeridos
@RequiredArgsConstructor
public class UnidadeSaudeService implements UnidadeSaudeIService {

    // Repositório injetado para operações de persistência de unidades de saúde
    private final UnidadesSaudeRepository unidadesSaudeRepository;

    // Retorna uma lista paginada de todas as unidades de saúde
    @Override
    public Page<UnidadesSaude> findAll(Pageable pageable) {
        // Usa o repositório para buscar unidades com suporte a paginação
        return unidadesSaudeRepository.findAll(pageable);
    }

    // Busca uma unidade de saúde específica pelo ID
    @Override
    public UnidadesSaude findById(Long id) {
        // Tenta encontrar a unidade pelo ID ou lança uma exceção se não existir
        return unidadesSaudeRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Unidade de id " + id + " não encontrado"));
    }

    // Salva uma nova unidade de saúde no banco de dados
    @Transactional // Garante que a operação seja executada em uma transação
    @Override
    public UnidadesSaude save(UnidadesSaude unidadesSaude) {
        // Persiste a unidade no banco e retorna a entidade salva
        return unidadesSaudeRepository.save(unidadesSaude);
    }

    // Remove uma unidade de saúde pelo telefone
    @Transactional // Assegura a integridade da exclusão
    @Override
    public void delete(String telefone) {
        // Busca a unidade pelo telefone ou lança uma exceção se não encontrada
        UnidadesSaude unidadeDelete = unidadesSaudeRepository.findByTelefone(telefone)
                .orElseThrow(() -> new BusinessException("Unidade de telefone " + telefone + " não encontrado"));
        // Deleta a unidade pelo ID obtido
        unidadesSaudeRepository.deleteById(unidadeDelete.getId());
    }

    // Atualiza os dados de uma unidade de saúde existente
    @Transactional // Mantém a consistência durante a atualização
    @Override
    public UnidadesSaude update(UnidadesSaude unidadesSaude) {
        // Busca a unidade atual pelo telefone ou lança uma exceção se não encontrada
        UnidadesSaude unidadesSaudeAtual = unidadesSaudeRepository.findByTelefone(unidadesSaude.getTelefone())
                .orElseThrow(() -> new BusinessException("Unidade de telefone " + unidadesSaude.getTelefone() + " não encontrado"));
        if (unidadesSaudeAtual != null) {
            // Mantém o ID e o endereço da unidade atual, atualizando os outros dados
            unidadesSaude.setId(unidadesSaudeAtual.getId());
            unidadesSaude.setEndereco(unidadesSaudeAtual.getEndereco());
            // Salva a unidade atualizada e retorna a entidade
            return unidadesSaudeRepository.save(unidadesSaude);
        }
        // Lança uma exceção caso a unidade não seja encontrada (redundante devido ao orElseThrow acima)
        throw new BusinessException("Unidade de Saúde com telefone: " + unidadesSaude.getTelefone() + " não encontrado");
    }

    // Busca uma unidade de saúde pelo nome
    @Override
    public UnidadesSaude findByNome(String nome) {
        // Tenta encontrar a unidade pelo nome ou lança uma exceção se não existir
        return unidadesSaudeRepository.findByNome(nome)
                .orElseThrow(() -> new BusinessException("Unidade de nome " + nome + " não encontrado"));
    }
}