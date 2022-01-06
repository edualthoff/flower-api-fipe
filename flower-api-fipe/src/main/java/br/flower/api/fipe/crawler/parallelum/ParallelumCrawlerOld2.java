package br.flower.api.fipe.crawler.parallelum;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.flower.api.fipe.crawler.ValueFipeVeiculo;
import br.flower.api.fipe.crawler.model.AnoModel;
import br.flower.api.fipe.crawler.model.MarcaModel;
import br.flower.api.fipe.crawler.model.ModeloModel;
import br.flower.api.fipe.crawler.model.ValorModel;
import br.flower.api.fipe.crawler.model.VeiculoModel;

/**
 * Crawler para api do Parallelum do @author deividfortuna
 * 
 * @git http://deividfortuna.github.io/fipe/
 * 
 *      Baixa as informações dos veiculos, modelos, anos, marcas e valores - Api
 *      referencia Tabela Fipe - Fipe
 * @author edu
 *
 */
public class ParallelumCrawlerOld2 implements ValueFipeVeiculo {
	private static final Logger log = LoggerFactory.getLogger(ParallelumCrawlerOld2.class);

	private String urlApi = "https://parallelum.com.br/fipe/api/v1/";
	private List<String> automovel = Arrays.asList("carros", "motos", "caminhoes");
	//private List<String> automovel = Arrays.asList("motos");
	private Map<String, Set<MarcaModel>> marcas = new HashMap<>();
	private Set<AnoModel> anos = new LinkedHashSet<>();
	private Set<ModeloModel> modelos = new LinkedHashSet<>();
	private Set<VeiculoModel> veiculoModel = new LinkedHashSet<>();

	private void todasAsMarcas() {
		ObjectMapper object = new ObjectMapper();
		System.out.println("todasAsMarcas - Start");
		try {
			for (String auto : automovel) {
				String todasMarca = jsoupCrawler(urlApi + auto + "/marcas");
				marcas.put(auto, object.readValue(todasMarca, new TypeReference<Set<MarcaModel>>() {}));
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Set<ModeloModel> modelosPorMarca(String codigoMarca, String auto) {
		Set<ModeloModel> modelos = new LinkedHashSet<>();
		String modelosProMarca = this.jsoupCrawler(urlApi + auto + "/marcas/" + codigoMarca + "/modelos");
		if(modelosProMarca == null) {
			log.debug("modelosPorMarca -- null {}", urlApi + auto + "/marcas/" + codigoMarca + "/modelos");
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> allModelosAndAno = new HashMap<>();
			allModelosAndAno = mapper.readValue(modelosProMarca, new TypeReference<Map<String, Object>>() {});
			modelos.addAll(mapper.readValue(mapper.writeValueAsString(allModelosAndAno.get("modelos")), new TypeReference<Set<ModeloModel>>() {}));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return modelos;
	}
	
	public Set<AnoModel> anoPorModelo(String codigoMarca, String auto, Long modelo) {
		Set<AnoModel> ano = new LinkedHashSet<>();
		//System.out.println(" anoPorModelo link: "+urlApi + auto + "/marcas/" + codigoMarca + "/modelos/" + modelo+ "/anos/");
		String anoPorModelo = this.jsoupCrawler(urlApi + auto + "/marcas/" + codigoMarca + "/modelos/" + modelo+ "/anos");
		
		if(anoPorModelo == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			//System.out.println("anoPorModelo - metodo - "+anoPorModelo);
			ano.addAll(mapper.readValue(anoPorModelo, new TypeReference<Set<AnoModel>>() {}));
			/*
			Map<String, Object> allModelosAndAno = new HashMap<>();
			allModelosAndAno = mapper.readValue(anoPorModelo, new TypeReference<Map<String, Object>>() {});
			ano.addAll(mapper.readValue(mapper.writeValueAsString(allModelosAndAno.get("anos")), new TypeReference<Set<AnoModel>>() {}));*/
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ano;
	}
	/*
	// ex: parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/2014-3
	// Consulta ano ex:
	// parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos
	private void startScanFipeVeiculo() {
		ObjectMapper mapper = new ObjectMapper();
		String urlConsulta;
		System.out.println("veiculoAndValor - Start");
		try {
			//MarcaModel marcas : this.marcas
			for () {
				 
				//System.out.println("veiculoAndValor - 1 For");
				int i = 1;
				for (Iterator<String> it = automovel.iterator(); it.hasNext(); i++) {
					String auto = it.next();
					log.debug("Car run momment: {}, i value: {}",auto, i);
					Set<ModeloModel> modeloLoop = this.modelosPorMarca(marcas.getCodigo(), auto);
					if(Objects.nonNull(modeloLoop)) {
						this.modelos.addAll(modeloLoop);
						//System.out.println("veiculoAndValor - if - modeloLoop "+modeloLoop.size());

						for(ModeloModel modFor : modeloLoop) {
							VeiculoModel veiculoNew = null;
							Set<AnoModel> anoM = this.anoPorModelo(marcas.getCodigo(), auto, modFor.getCodigo());
							//System.out.println("veiculoAndValor - if - modeloLoop - For 3 "+anoM.size());
							this.anos.addAll(anoM);
							for(AnoModel anoFor : anoM) {
								//System.out.println("veiculoAndValor ultimo for ANO "+anoFor.getNome()+" marca:"+marcas.getNome());
								urlConsulta = this.jsoupCrawler(urlApi + auto + "/marcas/" + marcas.getCodigo()
												+ "/modelos/" + modFor.getCodigo() + "/anos/" + anoFor.getCodigo());
									Map<String, String> json = mapper.readValue(urlConsulta, new TypeReference<Map<String, String>>() { });
									if (veiculoNew == null) {
										veiculoNew = new VeiculoModel();
										veiculoNew.setMarca(marcas);
										veiculoNew.setModelo(modFor);
										veiculoNew.setTipoVeiculo(i);
										veiculoNew.setSiglaCombustivel(json.get("SiglaCombustivel"));
										veiculoNew.setCombustivel(json.get("Combustivel"));
										veiculoNew.setCodigoFipe(json.get("CodigoFipe"));
										veiculoNew.setValorModel(new LinkedHashSet<>());
										System.out.println("New Veiculo " + veiculoNew.toString());
									}
									veiculoNew.getValorModel().add(new ValorModel(anoFor, json.get("Valor"),
											Integer.parseInt(json.get("AnoModelo")), json.get("MesReferencia")));
									//System.out.println("anos - v: "+anoFor.getNome());
							}
							//veiculoNew.setValorModel(valorModel);
							this.veiculoModel.add(veiculoNew);							
						}
					}
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("veiculoAndValor - End");
	}
*/
	private String jsoupCrawler(String url) {
		try {
			return Jsoup.connect(url).timeout(1000000).ignoreContentType(true)
			        .header("Content-Type", "application/json")
		            .header("Accept", "application/json")
					.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:40.0) Gecko/20100101 Firefox/40.0")
					.get().body().text();
		} catch (IOException e) {
			return null;
			// e.printStackTrace();
		}
	}

	@Override
	public void init() {
		this.todasAsMarcas();
		//this.startScanFipeVeiculo();
	}

	@Override
	public CompletableFuture<Set<VeiculoModel>> listVeiculo() {
		CompletableFuture<Set<VeiculoModel>> c = new CompletableFuture<Set<VeiculoModel>>();
		c.complete(this.veiculoModel);
		return c;
	}

	@Override
	public Set<VeiculoModel> listSetVeiculo() {
		return this.veiculoModel;
	}
}
