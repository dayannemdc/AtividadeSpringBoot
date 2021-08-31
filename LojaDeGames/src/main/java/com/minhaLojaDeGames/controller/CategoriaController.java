package com.minhaLojaDeGames.controller;

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

import com.minhaLojaDeGames.model.Categoria;
import com.minhaLojaDeGames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private @Autowired CategoriaRepository repository;

	@GetMapping("/todas")
	public ResponseEntity< List<Categoria>> findAllCategoria() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id_categoria}")
	public ResponseEntity<Categoria> buscarfindByIDCategoria(
			@PathVariable(value = "id_categoria") Long idCategoria) {
		Optional<Categoria> objetoCategoria = repository.findById(idCategoria);
		if (objetoCategoria.isPresent()) {
			return ResponseEntity.status(200).body(objetoCategoria.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/tipo/{descrição}")
	public ResponseEntity<List<Categoria>> findByDescricaoCategoria(@PathVariable(value = "descrição") String tipo) {
		List<Categoria> objetoCategoria = repository.findAllByTipoContainingIgnoreCase(tipo);
		if (objetoCategoria.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoCategoria);
		}
	}

	@PostMapping("/post")
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria novaCategoria) {
		return ResponseEntity.status(201).body(repository.save(novaCategoria));
	}
	
	@PutMapping("/put")
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoriaParaAtualizar) {
		return ResponseEntity.status(201).body(repository.save(categoriaParaAtualizar));
	}

	@DeleteMapping("/delete/{id_categoria}")
	public void deleteCategoria(@PathVariable(value = "id_categoria") Long idCategoria) {
		repository.deleteById(idCategoria);
	}
}
