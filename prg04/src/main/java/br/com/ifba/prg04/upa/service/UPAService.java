package br.com.ifba.prg04.upa.service;

// Importações necessárias para o funcionamento do serviço
import br.com.ifba.prg04.infrastructure.exception.BusinessException; // Exceção personalizada para erros de negócio
import br.com.ifba.prg04.upa.entity.UPA; // Entidade que representa uma UPA
import br.com.ifba.prg04.upa.repository.UPARepository; // Repositório para operações de banco de dados
import lombok.RequiredArgsConstructor; // Anotação do Lombok para gerar construtor com dependências
import org.springframework.data.domain.Page; // Classe para paginação de resultados
import org.springframework.data.domain.Pageable; // Interface para configurações de paginação
import org.springframework.stereotype.Service; // Anotação para marcar como serviço Spring
import org.springframework.transaction.annotation.Transactional; // Anotação para controle transacional

// Marca a classe como um serviço Spring e utiliza Lombok para injetar dependências
@Service
@RequiredArgsConstructor
// Classe de serviço para gerenciar operações relacionadas a UPAs
public class UPAService implements UPAIService {
    // Repositório injetado automaticamente pelo Lombok
    private final UPARepository upaRepository;

    // Método para buscar todas as UPAs com suporte a paginação
    @Override
    public Page<UPA> findAll(Pageable pageable) {
        // Retorna uma página de UPAs usando o repositório
        return upaRepository.findAll(pageable);
    }

    // Método para buscar uma UPA por ID
    @Override
    public UPA findById(Long id) {
        // Busca a UPA pelo ID; lança exceção se não encontrada
        return upaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Upa de id " + id + " não encontrada"));
    }

    // Método para salvar uma nova UPA
    @Override
    @Transactional // Garante que a operação seja executada em uma transação
    public UPA save(UPA upa) {
        // Salva a UPA no banco de dados e retorna a entidade persistida
        return upaRepository.save(upa);
    }

    // Método para excluir uma UPA por ID
    @Override
    @Transactional // Garante que a exclusão seja transacional
    public void delete(Long id) {
        // Exclui a UPA pelo ID usando o repositório
        upaRepository.deleteById(id);
    }

    // Método para atualizar uma UPA existente
    @Override
    @Transactional // Garante que a atualização seja transacional
    public UPA update(UPA upa) {
        // Busca a UPA existente pelo telefone; lança exceção se não encontrada
        UPA upaFound = upaRepository.findUPAByTelefone(upa.getTelefone())
                .orElseThrow(() -> new BusinessException("UPA de telefone " + upa.getTelefone() + " não encontrada"));

        // Verifica se a UPA foi encontrada e atualiza os campos necessários
        if (upaFound != null) {
            upa.setId(upaFound.getId()); // Mantém o ID da UPA existente
            upa.setEndereco(upaFound.getEndereco()); // Preserva o endereço original
        }

        // Salva as alterações no banco de dados e retorna a UPA atualizada
        return upaRepository.save(upa);
    }
}