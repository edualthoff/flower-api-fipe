package br.flower.api.fipe.veiculo;

import java.io.Serializable;
import java.util.List;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.marca.Marca;
import br.flower.api.fipe.modelo.Modelo;

public interface VeiculoService extends Serializable {

	List<Marca> todasAsMarcas();
	List<Modelo> todasOsModelosPorMarca(Long marcaId);
	List<Ano> todasOsAnosDoModelosDaMarca(Long marcaId, Long modeloId);

}
