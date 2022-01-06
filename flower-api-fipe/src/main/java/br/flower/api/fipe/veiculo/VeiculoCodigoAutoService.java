package br.flower.api.fipe.veiculo;

import br.flower.api.fipe.dto.VeiculoDto;

public interface VeiculoCodigoAutoService extends VeiculoService {

	VeiculoDto buscarPorCodigoAuto(Long codigoAuto);
	
}
