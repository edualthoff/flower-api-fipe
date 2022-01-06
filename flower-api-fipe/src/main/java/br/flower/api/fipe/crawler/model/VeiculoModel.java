package br.flower.api.fipe.crawler.model;

import java.util.Set;

import lombok.Data;

@Data
public class VeiculoModel {

	private MarcaModel marca;
	private ModeloModel modelo;
	private String siglaCombustivel;
	private int tipoVeiculo;
	private String combustivel;
	private String codigoFipe;
	private Set<ValorModel> valorModel;

	
}
