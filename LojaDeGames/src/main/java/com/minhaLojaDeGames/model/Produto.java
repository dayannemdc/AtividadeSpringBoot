package com.minhaLojaDeGames.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "tb_produto")
public class Produto {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idProduto;
	private @NotBlank String nome;
	private @NotBlank String descricao;
	private @NotBlank double preco;
	
	@ManyToOne
	//@JoinColumn(name = "categoria_id")
	public Categoria categoriaRelacionada;
	
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Categoria getCategoriaRelacionada() {
		return categoriaRelacionada;
	}
	public void setCategoriaRelacionada(Categoria categoriaRelacionada) {
		this.categoriaRelacionada = categoriaRelacionada;
	}
		
}
