package br.flower.api.fipe.valor;

import org.springframework.data.jpa.repository.JpaRepository;

import br.flower.api.fipe.valor.ValorVeiculo.ValorVeiculoId;
import br.flower.api.fipe.veiculo.Veiculo;

public interface ValorVeiculoRepository extends JpaRepository<ValorVeiculo, ValorVeiculoId>{

	
	ValorVeiculo findByVeiculo(Veiculo veiculo);
}
