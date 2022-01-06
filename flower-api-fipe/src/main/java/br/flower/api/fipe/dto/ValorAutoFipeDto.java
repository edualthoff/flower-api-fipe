package br.flower.api.fipe.dto;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ValorAutoFipeDto {

	private Long codigoVeiculo;
	private String nomeMarca;
	private String nomeModelo;
	private LocalDate mesReferencia;
	private String anoECombustivel;
	private String valor;
	private String codigoFipe;
	private int anoVeiculo;
	
	public ValorAutoFipeDto(Long codigoVeiculo, String nomeMarca, String nomeModelo, LocalDate mesReferencia,
			int anoVeiculo, String anoECombustivel, String valor, String codigoFipe) {
		super();
		this.codigoVeiculo = codigoVeiculo;
		this.nomeMarca = nomeMarca;
		this.nomeModelo = nomeModelo;
		this.mesReferencia = mesReferencia;
		this.anoVeiculo = anoVeiculo;
		this.anoECombustivel = anoECombustivel;
		this.valor = valor;
		this.codigoFipe = codigoFipe;
	}
	
	
}
