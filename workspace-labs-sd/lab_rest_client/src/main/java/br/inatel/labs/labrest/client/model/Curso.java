package br.inatel.labs.labrest.client.model;

import java.util.Objects;


public class Curso {
	
	private Long id;
	
	private String descricao;
	
	private Integer cargaHoraria;

	// ctrl + 3 = gcuf => gera construtor
	
	// Construtores
	
	public Curso(Long id, String descricao, Integer cargaHoraria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.cargaHoraria = cargaHoraria;
	}
	
	
	public Curso() {
		super();
	}


	// Acessores (getter and setter)
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Integer getCargaHoraria() {
		return cargaHoraria;
	}


	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", descricao=" + descricao + ", cargaHoraria=" + cargaHoraria + "]";
	}
	
}
