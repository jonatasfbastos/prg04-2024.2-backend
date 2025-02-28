package br.com.ifba.prg04.hospital.service;

// Importações necessárias para o serviço
import br.com.ifba.prg04.hospital.entity.Hospital;
import br.com.ifba.prg04.hospital.repository.HospitalRepository;
import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Marca a classe como um serviço gerenciado pelo Spring
@Service
// Gera um construtor automático com os campos finais requeridos
@RequiredArgsConstructor
public class HospitalService implements HospitalIService {
    // Repositório injetado para operações de persistência de hospitais
    private final HospitalRepository hospitalRepository;

    // Retorna uma lista paginada de todos os hospitais
    @Override
    public Page<Hospital> findAll(Pageable pageable) {
        // Usa o repositório para buscar hospitais com suporte a paginação
        return hospitalRepository.findAll(pageable);
    }

    // Busca um hospital específico pelo ID
    @Override
    public Hospital findById(Long id) {
        // Tenta encontrar o hospital ou lança uma exceção se não existir
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Hospital de id " + id + " não encontrado"));
    }

    // Salva um novo hospital no banco de dados
    @Override
    @Transactional // Garante que a operação seja executada em uma transação
    public Hospital save(Hospital hospital) {
        // Persiste o hospital no banco e retorna a entidade salva
        return hospitalRepository.save(hospital);
    }

    // Remove um hospital pelo ID
    @Override
    @Transactional // Assegura a integridade da exclusão
    public void delete(Long Id) {
        // Deleta o hospital do banco de dados usando o ID fornecido
        hospitalRepository.deleteById(Id);
    }

    // Atualiza os dados de um hospital existente
    @Override
    @Transactional // Mantém a consistência durante a atualização
    public Hospital update(Hospital hospital) {
        // Salva as alterações do hospital e retorna a entidade atualizada
        return hospitalRepository.save(hospital);
    }
}