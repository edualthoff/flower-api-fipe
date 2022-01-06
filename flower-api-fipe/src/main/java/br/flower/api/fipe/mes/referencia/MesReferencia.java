package br.flower.api.fipe.mes.referencia;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "fp_mes_referencia")
public class MesReferencia implements Serializable {
	private static final long serialVersionUID = 8345913553748466018L;

	@Id
	@Column(name = "mes_id")
	//@JsonFormat(pattern = "MMMM/yyyy")
	private LocalDate mesId;
	
	public MesReferencia() {
		super();
	}

	public MesReferencia(LocalDate mesId) {
		super();
		this.mesId = mesId;
	}
	
}
