package com.minhaLojaDeGames.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhaLojaDeGames.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	List<Categoria> findAllByTipoContainingIgnoreCase(String tipo);
	
	Optional<Categoria> findByTipo(String tipo);
}
