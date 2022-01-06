package br.flower.api.fipe.veiculo.moto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.marca.Marca;
import br.flower.api.fipe.modelo.Modelo;
import br.flower.api.fipe.veiculo.Veiculo;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Veiculo.VeiculoPk> {

	@Query("SELECT DISTINCT u.marca from Moto u")
	List<Marca> findDistinctMarcaId();
	
	List<Modelo> findDistinctByMarcaMarcaId(Long marca);
	
	@Query("SELECT DISTINCT u.modelo from Moto u WHERE u.marca.id=?1")
	List<Modelo> findDistinctModelo(Long marcaId);
	
	@Query("SELECT DISTINCT u.anoVeiculo from Moto u WHERE u.marca.id=?1 AND u.modelo.id=?2")
	List<Ano> findDistinctAnosPorModelo(Long marcaId, Long modeloId);
 	
}
