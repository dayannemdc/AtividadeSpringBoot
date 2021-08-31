package com.farmacia.farmacia.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmacia.model.Produto;
import com.farmacia.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private @Autowired ProdutoRepository repository;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Produto>> todosProdutos(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id_produto")
	public ResponseEntity<Produto> buscaPorId(@PathVariable(value = "id_produto") Long idProdutoFarmacia){
		Optional<Produto> objetoProduto = repository.findById(idProdutoFarmacia);
			if (objetoProduto.isPresent()) {
				return ResponseEntity.ok(objetoProduto.get());
			}else {
				return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/nome/{descrição}")
	public ResponseEntity<List<Produto>> buscarPorDescricao(@PathVariable(value = "descição")String nome){
		List<Produto> objetoProduto = repository.findAllByNomeContainingIgnoreCase(nome);
		if(objetoProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoProduto);
		}
	}
	
	@PostMapping("/postar")
	public ResponseEntity<Produto> postarProduto(@Valid @RequestBody Produto novoPoduto) {
		return ResponseEntity.status(201).body(repository.save(novoPoduto));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Produto> atualizarProduto (@Valid @RequestBody Produto produtoParaAtualizar){
		return ResponseEntity.status(201).body(repository.save(produtoParaAtualizar));
	}
	
	@DeleteMapping("/deletar/{id_produto}")
	public void deletarProduto (@PathVariable(value = "id_produto") Long idProduto) {
		repository.deleteById(idProduto);
	}
}
