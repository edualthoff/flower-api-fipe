package br.flower.api.fipe.marca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{

	//List<Marca> findAllByMarcaId(List<Marca> marcas);
	
}
