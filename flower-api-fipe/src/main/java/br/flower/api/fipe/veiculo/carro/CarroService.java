package br.flower.api.fipe.veiculo.carro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.marca.Marca;
import br.flower.api.fipe.modelo.Modelo;
import br.flower.api.fipe.veiculo.Veiculo.VeiculoPk;
import br.flower.api.fipe.veiculo.VeiculoService;


@Service
public class CarroService implements VeiculoService {
	private static final long serialVersionUID = 4346358496994487844L;

	@Autowired private CarroRepository carroRepository;
	

	public List<Marca> todasAsMarcas() {
		return this.carroRepository.findDistinctMarcaId();
	}
	
	public List<Modelo> todasOsModelosPorMarca(Long marcaId) {
		return this.carroRepository.findDistinctModelo(marcaId);
	}
	
	public List<Ano> todasOsAnosDoModelosDaMarca(Long marcaId, Long modeloId) {
		return this.carroRepository.findDistinctAnosPorModelo(marcaId, modeloId);
	}
	
	public Carro carroPorAnosDoModelosDaMarca(Long marcaId, Long modeloId, String anoId) {
		return this.carroRepository.findById(new VeiculoPk(marcaId, modeloId, anoId)).get();
	}
}
