package com.minhaLojaDeGames.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhaLojaDeGames.model.Produto;
import com.minhaLojaDeGames.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private @Autowired ProdutoRepository repository;
	
	public Optional<Object> atualizarProduto (Produto produtoParaAtualizar){
		return repository.findById(produtoParaAtualizar.getIdProduto()).map(produtoExistente -> {
		return Optional.empty();
		}).orElseGet(()->{
			return Optional.ofNullable(repository.save(produtoParaAtualizar));
		});
	}
}
