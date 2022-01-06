package br.flower.api.fipe.veiculo.caminhao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.marca.Marca;
import br.flower.api.fipe.modelo.Modelo;
import br.flower.api.fipe.veiculo.VeiculoService;

@Service
public class CaminhaoService implements VeiculoService {
	private static final long serialVersionUID = -694497327048557840L;

	
	@Autowired private CaminhaoRepository caminhaoRepository;
	

	public List<Marca> todasAsMarcas() {
		return this.caminhaoRepository.findDistinctMarcaId();
	}
	
	public List<Modelo> todasOsModelosPorMarca(Long marcaId) {
		return this.caminhaoRepository.findDistinctModelo(marcaId);
	}
	
	public List<Ano> todasOsAnosDoModelosDaMarca(Long marcaId, Long modeloId) {
		return this.caminhaoRepository.findDistinctAnosPorModelo(marcaId, modeloId);
	}

	
}
