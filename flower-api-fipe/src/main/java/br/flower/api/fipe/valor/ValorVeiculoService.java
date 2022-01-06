package br.flower.api.fipe.valor;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.flower.api.fipe.dto.ValorAutoFipeDto;
import br.flower.api.fipe.veiculo.Veiculo;

@Service
public class ValorVeiculoService implements Serializable {
	private static final long serialVersionUID = 4762959949869995985L;

	@Autowired
	private ValorVeiculoRepository valorVeiculoRepository;

	
	
	public ValorVeiculo findVeiculoValorFipe(Veiculo veiculos) {
		return this.valorVeiculoRepository.findByVeiculo(veiculos);
	}
	
	
	public ValorAutoFipeDto valorAutoFipeDto(Veiculo veiculos) {
		ValorVeiculo valorVeiculo = this.findVeiculoValorFipe(veiculos);
		return new ValorAutoFipeDto(
				valorVeiculo.getVeiculo().getCodigoAuto(),
				valorVeiculo.getVeiculo().getMarca().getName(),
				valorVeiculo.getVeiculo().getModelo().getModelo(),
				valorVeiculo.getMesReferencia().getMesId(),
				valorVeiculo.getVeiculo().getAnoModelo(),
				valorVeiculo.getVeiculo().getAnoVeiculo().getNome(),
				valorVeiculo.getValor(),
				valorVeiculo.getVeiculo().getModelo().getCodigoFipe()
				);
		
	}
}
