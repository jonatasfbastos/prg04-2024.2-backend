package br.com.ifba.prg04.familia.service;

import br.com.ifba.prg04.familia.entity.Familia;

import java.util.List;

public interface FamiliaIService {

   public abstract Familia salvar(Familia familia);
   public abstract Familia update(int id, Familia familia);
   public abstract Familia findById(int id);
   public abstract List<Familia> findAll();
}
