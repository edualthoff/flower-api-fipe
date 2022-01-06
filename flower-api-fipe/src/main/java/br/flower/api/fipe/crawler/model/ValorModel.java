package br.flower.api.fipe.crawler.model;

import lombok.Data;

@Data
public class ValorModel {

	private AnoModel ano;
	private String valor;
	private int anoModelo;
	private String mesReferencia;
	
	public ValorModel(AnoModel ano, String valor, int anoModelo, String mesReferencia) {
		super();
		this.ano = ano;
		this.valor = valor;
		this.anoModelo = anoModelo;
		this.mesReferencia = mesReferencia;
	}

}
