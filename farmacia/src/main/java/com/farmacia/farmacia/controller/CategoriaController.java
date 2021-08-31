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

import com.farmacia.farmacia.model.Categoria;
import com.farmacia.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private @Autowired CategoriaRepository repository;
	
	@GetMapping("/todas")
	public ResponseEntity<List<Categoria>> todasCategorias(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id_categFarmacia}")
	public ResponseEntity<Categoria> porId(@PathVariable(value = "id_categFarmacia") Long idCategFarmacia){
		Optional<Categoria> objetoCategoria = repository.findById(idCategFarmacia);
		if (objetoCategoria.isPresent()) {
			return ResponseEntity.ok(objetoCategoria.get());
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@PostMapping("/nova")
	public ResponseEntity<Categoria> novaCategoria(@Valid @RequestBody Categoria novaCategFarmacia){
		return ResponseEntity.ok(repository.save(novaCategFarmacia));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Categoria> atualizarCategoria(@Valid @RequestBody Categoria atualizarCategFarmacia){
		return ResponseEntity.ok(repository.save(atualizarCategFarmacia));
	}
	
	@DeleteMapping("/deletar/{id_categoria}")
	public void deletarCategFarmacia(@PathVariable(value = "id_categoria") Long idCategoriaFarmacia) {
		repository.deleteById(idCategoriaFarmacia);
	}
}
