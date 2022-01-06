package br.flower.api.fipe.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Veiculo.VeiculoPk> {

	Veiculo findByCodigoAuto(Long codigoAuto);
	
}
