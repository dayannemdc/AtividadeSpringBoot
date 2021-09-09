package com.minhaLojaDeGames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minhaLojaDeGames.model.Produto;
import com.minhaLojaDeGames.repository.ProdutoRepository;
import com.minhaLojaDeGames.service.ProdutoService;

@RestController
@RequestMapping("produto")
public class ProdutoController {

	private @Autowired ProdutoRepository repository;
	private @Autowired ProdutoService service;
	
	@GetMapping("/todos")
	public List<Produto> findAllProdutos() {
		return repository.findAll();
	}

	@GetMapping("/{id_produto}")
	public ResponseEntity<Produto> buscarfindByIDProduto(
			@PathVariable(value = "id_produto") Long idProduto) {
		Optional<Produto> objetoProduto = repository.findById(idProduto);
		if (objetoProduto.isPresent()) {
			return ResponseEntity.status(200).body(objetoProduto.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/titulo/{descrição}")
	public ResponseEntity<List<Produto>> findByDescricaoTitulo(@PathVariable(value = "descrição") String nome) {
		List<Produto> objetoProduto = repository.findAllByNomeContainingIgnoreCase(nome);
		if (objetoProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoProduto);
		}
	}

	@PostMapping("/post")
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto novoProduto) {
		return ResponseEntity.status(201).body(repository.save(novoProduto));
	}
	
	@PutMapping("/put")
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produtoParaAtualizar) {
		Optional<?> objetoProduto = service.atualizarProduto(produtoParaAtualizar);
		if(objetoProduto.isEmpty()) {
			return ResponseEntity.status(400).build();
		}else {
			return ResponseEntity.status(201).body(repository.save(produtoParaAtualizar));
		}
	}

	@DeleteMapping("/delete/{id_produto}")
	public ResponseEntity<Produto> deleteProduto(@PathVariable(value = "id_produto") Long idProduto) {
		Optional<Produto> objetoProduto = repository.findById(idProduto);
		if(objetoProduto.isPresent()) {
			repository.deleteById(idProduto);
			return ResponseEntity.status(200).build();
		}else {
			return ResponseEntity.status(400).build();
		}
	}
}
