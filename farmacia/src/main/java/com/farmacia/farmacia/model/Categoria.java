package com.farmacia.farmacia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categoria_farmacia")
public class Categoria {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoriaFarmacia;
	@NotBlank
	private String descricao;
	
	@OneToMany (mappedBy = "categoriaFarmacia")
	private List<Produto> produto = new ArrayList<>();

	public Long getIdCategoriaFarmacia() {
		return idCategoriaFarmacia;
	}

	public void setIdCategoriaFarmacia(Long idCategoriaFarmacia) {
		this.idCategoriaFarmacia = idCategoriaFarmacia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
}
