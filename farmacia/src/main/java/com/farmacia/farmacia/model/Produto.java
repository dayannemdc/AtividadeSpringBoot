package com.farmacia.farmacia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table( name = "tb_produto_farmacia")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProdutoFarmacia;
	@NotBlank
	private String nome;
	@NotBlank
	private String dados;
	@NotBlank
	private double preco;
	
	@ManyToOne
	public Categoria categoriaFarmacia;

	public Long getIdProdutoFarmacia() {
		return idProdutoFarmacia;
	}

	public void setIdProdutoFarmacia(Long idProdutoFarmacia) {
		this.idProdutoFarmacia = idProdutoFarmacia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Categoria getCategoriaFarmacia() {
		return categoriaFarmacia;
	}

	public void setCategoriaFarmacia(Categoria categoriaFarmacia) {
		this.categoriaFarmacia = categoriaFarmacia;
	}
	
	
}
