package br.flower.api.fipe.veiculo;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.dto.VeiculoDto;
import br.flower.api.fipe.marca.Marca;
import br.flower.api.fipe.modelo.Modelo;

@Service
public class VeiculoServiceImp implements VeiculoCodigoAutoService {
	private static final long serialVersionUID = -5520810169617954726L;

	@Autowired
	private VeiculoRepository veiculoRepositor;
	
	public Veiculo save(Veiculo veiculo) {
		return veiculoRepositor.saveAndFlush(veiculo);
	}
	
	
	public List<Veiculo> saveAll(Set<Veiculo> veiculos) {
		return veiculoRepositor.saveAllAndFlush(veiculos);
	}

	@Override
	public VeiculoDto buscarPorCodigoAuto(Long codigoAuto) {
		Veiculo veiculo = veiculoRepositor.findByCodigoAuto(codigoAuto);
		return new VeiculoDto(veiculo.getCodigoAuto(), veiculo.getMarca().getName(),
				veiculo.getModelo().getModelo(), veiculo.getAnoModelo(), 
				veiculo.getAnoVeiculo().getNome(), veiculo.getModelo().getCodigoFipe());
	}

	@Override
	public List<Marca> todasAsMarcas() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Modelo> todasOsModelosPorMarca(Long marcaId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Ano> todasOsAnosDoModelosDaMarca(Long marcaId, Long modeloId) {
		// TODO Auto-generated method stub
		return null;
	}
}
