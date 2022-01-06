package br.flower.api.fipe.marca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fp_marca")
public class Marca {

	@Id
	@Column(name = "marca_id")
	private Long marcaId;
	
	@Column(name = "nome")
	private String name;

	public Marca(Long marcaId, String name) {
		super();
		this.marcaId = marcaId;
		this.name = name;
	}

	public Marca(Long marcaId) {
		super();
		this.marcaId = marcaId;
	}
	
	public Marca() {
		super();
	}
	
	
}
