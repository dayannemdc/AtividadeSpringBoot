package com.minhaLojaDeGames.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhaLojaDeGames.model.Categoria;
import com.minhaLojaDeGames.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private @Autowired CategoriaRepository repository;
	
	public Optional<Categoria> atualizarCategoria (Categoria categoriaParaAtualizar){
		return repository.findById(categoriaParaAtualizar.getIdCategoria()).map(categoriaExistente ->{
			return Optional.ofNullable(repository.save(categoriaParaAtualizar));
		}).orElseGet(()->{
			return Optional.empty();
		});
	}
}
