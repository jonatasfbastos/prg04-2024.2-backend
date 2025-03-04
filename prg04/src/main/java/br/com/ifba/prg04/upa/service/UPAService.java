package br.com.ifba.prg04.upa.service;

import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.upa.entity.UPA;
import br.com.ifba.prg04.upa.repository.UPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UPAService implements UPAIService{
    private final UPARepository upaRepository;
    @Override
    public Page<UPA> findAll(Pageable pageable) {
        return upaRepository.findAll(pageable);
    }

    @Override
    public UPA findById(Long id) {
        return upaRepository.findById(id).orElseThrow(() -> new BusinessException("Upa de id " + id + " não encontrada"));
    }

    @Override
    @Transactional
    public UPA save(UPA upa) {
        return upaRepository.save(upa);
    }

    @Override
    @Transactional
    public void delete(Long Id) {
        upaRepository.deleteById(Id);
    }

    @Override
    @Transactional
    public UPA update(UPA upa) {
        return upaRepository.save(upa);
    }
}
