package br.com.ifba.prg04.ubs.service;

// Importações necessárias para o serviço
import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.ubs.entity.UBS;
import br.com.ifba.prg04.ubs.repository.UBSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Marca a classe como um serviço gerenciado pelo Spring
@Service
// Gera automaticamente um construtor com os campos finais requeridos
@RequiredArgsConstructor
public class UBSService implements UBSIService {
    // Repositório injetado para operações de persistência de UBS
    private final UBSRepository ubsRepository;

    // Retorna uma lista paginada de todas as UBS
    @Override
    public Page<UBS> findAll(Pageable pageable) {
        // Usa o repositório para buscar UBS com suporte a paginação
        return ubsRepository.findAll(pageable);
    }

    // Busca uma UBS específica pelo ID
    @Override
    public UBS findById(Long id) {
        // Tenta encontrar a UBS ou lança uma exceção se não existir
        return ubsRepository.findById(id)
                .orElseThrow(() -> new BusinessException("UBS de id " + id + " não encontrado"));
    }

    // Salva uma nova UBS no banco de dados
    @Override
    @Transactional // Garante que a operação seja executada em uma transação
    public UBS save(UBS ubs) {
        // Persiste a UBS no banco e retorna a entidade salva
        return ubsRepository.save(ubs);
    }

    // Remove uma UBS pelo ID
    @Override
    @Transactional // Assegura a integridade da exclusão
    public void delete(Long Id) {
        // Deleta a UBS do banco de dados usando o ID fornecido
        ubsRepository.deleteById(Id);
    }

    // Atualiza os dados de uma UBS existente
    @Override
    @Transactional // Mantém a consistência durante a atualização
    public UBS update(UBS ubs) {
        // Salva as alterações da UBS e retorna a entidade atualizada
        return ubsRepository.save(ubs);
    }
}