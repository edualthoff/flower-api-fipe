package br.flower.api.fipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.dto.ValorAutoFipeDto;
import br.flower.api.fipe.dto.VeiculoDto;
import br.flower.api.fipe.marca.Marca;
import br.flower.api.fipe.modelo.Modelo;
import br.flower.api.fipe.valor.ValorVeiculoService;
import br.flower.api.fipe.veiculo.Veiculo;
import br.flower.api.fipe.veiculo.Veiculo.VeiculoPk;
import br.flower.api.fipe.veiculo.VeiculoCodigoAutoService;
import br.flower.api.fipe.veiculo.VeiculoService;
import br.flower.api.fipe.veiculo.caminhao.CaminhaoService;
import br.flower.api.fipe.veiculo.carro.CarroService;
import br.flower.api.fipe.veiculo.moto.MotoService;

@RestController
@RequestMapping("/fipe")
public class RestFipeController {

	@Autowired private CarroService carroService;
	@Autowired private CaminhaoService caminhaoService;
	@Autowired private MotoService motoService;
	@Autowired private ValorVeiculoService valorVeiculoService;
	@Autowired private VeiculoCodigoAutoService veiculoCodigoAutoService;  
	
	@GetMapping("/{carros}/marcas")
	public List<Marca> marcaVeiculos(@PathVariable(value = "carros") String carros) {
		return this.selectTypeVeiculo(carros).todasAsMarcas();
	}
	
	@GetMapping("/{carros}/marcas/{marca}/modelos")
	public List<Modelo> modelosVeiculos(@PathVariable(value = "marca") Long marca,
			@PathVariable(value = "carros") String carros) {
		return this.selectTypeVeiculo(carros).todasOsModelosPorMarca(marca);
	}
	
	@GetMapping("/{carros}/marcas/{marca}/modelos/{modeloId}/anos")
	public List<Ano> anosPorModeloDeVeiculos(@PathVariable(value = "marca") Long marca,
			@PathVariable(value = "modeloId") Long modeloId,
			@PathVariable(value = "carros") String carros) {
		return this.selectTypeVeiculo(carros).todasOsAnosDoModelosDaMarca(marca, modeloId);
	}
	
	@GetMapping("/{carros}/marcas/{marca}/modelos/{modeloId}/anos/{anoid}")
	public ValorAutoFipeDto veiculoPorAnosPorModeloDeVeiculos(@PathVariable(value = "marca") Long marca, 
			@PathVariable(value = "modeloId") Long modeloId, 
			@PathVariable(value = "anoid") String anoid,
			@PathVariable(value = "carros") String carros) {
		return valorVeiculoService.valorAutoFipeDto(new Veiculo(new VeiculoPk(marca, modeloId, anoid)));
	}
	
	@GetMapping("/veiculo/{codigo}")
	public VeiculoDto buscarPorCodigoAuto(@PathVariable(value = "codigo") Long codigoAuto) {
		return veiculoCodigoAutoService.buscarPorCodigoAuto(codigoAuto);
	}
	
	private VeiculoService selectTypeVeiculo(String veiculo) {
		switch (veiculo) {
		case "carros":
			return this.carroService;
		case "caminhoes":
			return this.caminhaoService;
		case "motos":
			return this.motoService;
		}	
		return null;
	}
}
