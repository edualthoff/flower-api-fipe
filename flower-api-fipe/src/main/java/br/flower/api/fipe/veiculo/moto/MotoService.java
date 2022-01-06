package br.flower.api.fipe.veiculo.moto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.marca.Marca;
import br.flower.api.fipe.modelo.Modelo;
import br.flower.api.fipe.veiculo.VeiculoService;

@Service
public class MotoService implements VeiculoService  {
	private static final long serialVersionUID = 7112180828184645566L;

	@Autowired private MotoRepository motoRepository;
	

	public List<Marca> todasAsMarcas() {
		return this.motoRepository.findDistinctMarcaId();
	}
	
	public List<Modelo> todasOsModelosPorMarca(Long marcaId) {
		return this.motoRepository.findDistinctModelo(marcaId);
	}
	
	public List<Ano> todasOsAnosDoModelosDaMarca(Long marcaId, Long modeloId) {
		return this.motoRepository.findDistinctAnosPorModelo(marcaId, modeloId);
	}

}
