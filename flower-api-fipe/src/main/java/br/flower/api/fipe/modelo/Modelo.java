package br.flower.api.fipe.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fp_modelo")
public class Modelo implements Serializable{
	private static final long serialVersionUID = -7959761797309505523L;

	@Id
	@Column(name = "modelo_id")
	private Long modeloId;
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "codigo_fipe")
	private String codigoFipe;
	
	@Column(name = "combustivel")
	private String combustivel;
	
	@Column(name = "sigla_combustivel")
	private String siglaCombustivel;

	public Modelo() {
		super();
	}

	public Modelo(Long modeloId, String modelo, String codigoFipe, String combustivel, String siglaCombustivel) {
		super();
		this.modeloId = modeloId;
		this.modelo = modelo;
		this.codigoFipe = codigoFipe;
		this.combustivel = combustivel;
		this.siglaCombustivel = siglaCombustivel;
	}

	
}

