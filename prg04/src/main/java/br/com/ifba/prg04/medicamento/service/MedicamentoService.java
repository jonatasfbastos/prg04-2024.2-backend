package br.com.ifba.prg04.medicamento.service;

import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.medicamento.dao.MedicamentoDao;
import br.com.ifba.prg04.medicamento.entity.Medicamento;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
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


    /**
     * Autor: Henrique Martins
     * Pegas Todos os Medicamentossalva no banco de dados.
     * @param pageable elemento em paginação.
     * @return Medicamento findAll.
     */


    @Override
    public Page<Medicamento> findAll(Pageable pageable) {
        LOGGER.info("Listando todos os medicamentos");
        return medicamentoDao.findAll(pageable);
    }

    /**
     * Autor: Henrique Martins
     * Busca pelo ID do Medicamento no Banco de Dados.
     * @param id Pegando o id do medicamento para procura-lo.
     * @return Medicamento finById.
     */
    @Override
    public Optional<Medicamento> findById(Long id) {
        LOGGER.info("Buscando medicamento : {}", id);
        return medicamentoDao.findById(id);
    }

    /**
     * Autor: Henrique Martins
     * Cria um novo Medicamento e salva no banco de dados.
     * @param medicamento Objeto contendo os dados do medicamento.
     * @return Medicamento salvo.
     */
    @Override
    @Transactional
    public Medicamento save(Medicamento medicamento) {
        LOGGER.info("Salvando medicamento : {}", medicamento);
        return medicamentoDao.save(medicamento);
    }

    /**
     * Autor: Henrique Martins
     * Modifica Medicamento e salva no banco de dados, pesquisando pelo id.
     * @param medicamento Objeto contendo os dados do medicamento.
     * @return Medicamento salvo.
     */
    @Override
    @Transactional
    public Medicamento update(Long id, Medicamento medicamento) {
        /*Utilizando exception no procurar*/
        Medicamento medicamentoSalvo = medicamentoDao.findById(id)
                .orElseThrow(() -> new BusinessException("Medicamento não encontrado"));

        LOGGER.info("Atualizando medicamento : {}", medicamento);

        /*Copiando todos os atributos de uma objeto para outro utilizando Metodos do Spring Boot, eliminando o ID*/
        BeanUtils.copyProperties(medicamento, medicamentoSalvo, "id");

        return medicamentoDao.save(medicamentoSalvo);
    }

    /**
     * Autor: Henrique Martins
     * Deleta um Medicamento e elimina no banco de dados.
     * @param id do medicamento.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        /*Buscando pelo ID*/
        medicamentoDao.findById(id).orElseThrow(() -> new BusinessException("Medicamento Não encontrado"));

        LOGGER.debug("Deleting Medicamento : {}", id);

        medicamentoDao.deleteById(id);
    }
}
