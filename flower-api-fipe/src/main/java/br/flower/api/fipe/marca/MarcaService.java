package br.flower.api.fipe.marca;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService implements Serializable {
	private static final long serialVersionUID = 5769174636365192302L;

	@Autowired private MarcaRepository marcaRepository;
	
	public List<Marca> findAllMarca(){
		return this.marcaRepository.findAll();
	}
	
	public List<Marca> findAllPorMarca(List<Long> marcas){
		return this.marcaRepository.findAllById(marcas);
	}
}
