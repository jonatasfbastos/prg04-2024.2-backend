package br.com.ifba.prg04.medicamento.service;

import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.medicamento.dao.MedicamentoDao;
import br.com.ifba.prg04.medicamento.entity.Medicamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicamentoService implements IMedicamentoService{

    /*Logger da aplicação*/
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicamentoService.class);

    /*Repositório*/
    private final MedicamentoDao medicamentoDao;
    /*Mapper para transformar em objeto*/
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public Page<Medicamento> findAll(Pageable pageable) {
        return medicamentoDao.findAll(pageable);
    }

    @Override
    public Optional<Medicamento> findById(Long id) {
        return medicamentoDao.findById(id);
    }

    @Override
    @Transactional
    public Medicamento save(Medicamento medicamento) {
        return medicamentoDao.save(medicamento);
    }

    @Override
    @Transactional
    public Medicamento update(Long id, Medicamento medicamento) {

        /*Utilizando exception no procurar*/
        Medicamento medicamentoSalvo = medicamentoDao.findById(id)
                .orElseThrow(() -> new BusinessException("Medicamento não encontrado"));

        /*Copiando todos os atributos de uma objeto para outro utilizando o mapper*/
        BeanUtils.copyProperties(medicamento, medicamentoSalvo, "id");

        return medicamentoDao.save(medicamentoSalvo);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        medicamentoDao.findById(id).orElseThrow(() -> new BusinessException("Medicamento Não encontrado"));

        LOGGER.debug("Deleting Medicamento : {}", id);

        medicamentoDao.deleteById(id);
    }
}
