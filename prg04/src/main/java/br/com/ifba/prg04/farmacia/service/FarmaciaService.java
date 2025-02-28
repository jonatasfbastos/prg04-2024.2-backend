package br.com.ifba.prg04.farmacia.service;

import br.com.ifba.prg04.farmacia.entity.Farmacia;
import br.com.ifba.prg04.farmacia.repository.FarmaciaRepository;
import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Marca a classe como um serviço Spring
@Service
// Gera automaticamente um construtor com os campos finais requeridos
@RequiredArgsConstructor
public class FarmaciaService implements FarmaciaIService{

    // Repositório para acesso aos dados da farmácia, injetado automaticamente
    private final FarmaciaRepository farmaciaRepository;

    // Busca todas as farmácias com paginação
    @Override
    public Page<Farmacia> findAll(Pageable pageable) {
        // Retorna uma página de farmácias usando o repositório
        return farmaciaRepository.findAll(pageable);
    }

    // Busca uma farmácia específica pelo seu ID
    @Override
    public Farmacia findById(Long id) {
        // Tenta encontrar a farmácia pelo ID, lançando uma exceção se não encontrada
        return farmaciaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Farmacia com id " + id + " não encontrado"));
    }

    // Salva uma nova farmácia no banco de dados
    @Override
    @Transactional // Garante que a operação seja executada em uma transação
    public Farmacia save(Farmacia farmacia) {
        // Persiste a farmácia no banco de dados e retorna a entidade salva
        return farmaciaRepository.save(farmacia);
    }

    // Deleta uma farmácia pelo seu ID
    @Override
    @Transactional // Assegura a integridade da operação de exclusão
    public void delete(Long id) {
        // Remove a farmácia do banco de dados usando o ID fornecido
        farmaciaRepository.deleteById(id);
    }

    // Atualiza os dados de uma farmácia existente
    @Override
    @Transactional // Mantém a consistência dos dados durante a atualização
    public Farmacia update(Farmacia farmacia) {
        // Salva as alterações da farmácia e retorna a entidade atualizada
        return farmaciaRepository.save(farmacia);
    }
}