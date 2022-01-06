package br.flower.api.fipe.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class VeiculoDto {

	private Long codigoVeiculo;
	private String nomeMarca;
	private String nomeModelo;
	private String anoECombustivel;
	private String codigoFipe;
	private int anoVeiculo;
	
	public VeiculoDto(Long codigoVeiculo, String nomeMarca, String nomeModelo,
			int anoVeiculo, String anoECombustivel, String codigoFipe) {
		super();
		this.codigoVeiculo = codigoVeiculo;
		this.nomeMarca = nomeMarca;
		this.nomeModelo = nomeModelo;
		this.anoVeiculo = anoVeiculo;
		this.anoECombustivel = anoECombustivel;
		this.codigoFipe = codigoFipe;
	}
}
