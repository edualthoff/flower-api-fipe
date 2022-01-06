package br.flower.api.fipe.ano;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fp_ano")
public class Ano implements Serializable {
	private static final long serialVersionUID = -1577271943081700470L;
	
	@Id
	@Column(name = "ano_id")
	private String anoId;
	@Column(name = "nome")
	private String nome;
	
	
	public Ano() {
		super();
	}


	public Ano(String anoId, String nome) {
		super();
		this.anoId = anoId;
		this.nome = nome;
	}
	
	
}
