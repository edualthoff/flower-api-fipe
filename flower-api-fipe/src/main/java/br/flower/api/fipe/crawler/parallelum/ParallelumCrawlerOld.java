package br.flower.api.fipe.crawler.parallelum;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.jsoup.Jsoup;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class ParallelumCrawlerOld {

	private String urlApi = "https://parallelum.com.br/fipe/api/v1/";
	private List<String> automovel = Arrays.asList("carros", "caminhoes", "motos");
	private Set<MarcaModel> marcas;
	private Set<AnoModel> anos;
	private Set<ModeloModel> modelos;
	private Set<VeiculoModel> veiculoModel;

	private void todasAsMarcas() {
		marcas = new LinkedHashSet<>();
		ObjectMapper object = new ObjectMapper();
		try {
			for (String auto : automovel) {
				System.out.println("todasAsMarcas for auto");
				String todasMarca = jsoupCrawler(urlApi + auto + "/marcas");
				marcas = object.readValue(todasMarca, new TypeReference<Set<MarcaModel>>() {
				});
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void anoAndModeloPorMarca() {
		anos = new LinkedHashSet<>();
		modelos = new LinkedHashSet<>();
		ObjectMapper mapper = new ObjectMapper();

		String modelosAndAno;
		
		try {
			for (MarcaModel marcas : this.marcas) {
				Map<String, Object> allModelosAndAno = new HashMap<>();
				Iterator<String> i = this.automovel.iterator();
				boolean w = true;
				while (w) {
					i.hasNext();
					String auto = i.next();
					modelosAndAno = this.jsoupCrawler(urlApi + auto + "/marcas/" + marcas.getCodigo() + "/modelos");
					if (modelosAndAno != null) {
						allModelosAndAno = mapper.readValue(modelosAndAno, new TypeReference<Map<String, Object>>() {});
						modelos.addAll(mapper.readValue(mapper.writeValueAsString(allModelosAndAno.get("modelos")), new TypeReference<Set<ModeloModel>>() {}));
						anos.addAll(mapper.readValue(mapper.writeValueAsString(allModelosAndAno.get("anos")), new TypeReference<Set<AnoModel>>() {}));

						System.out.println("modelos total: "+ modelos.size()+" v: "+auto);
						System.out.println("anos total: "+ anos.size()+" v: "+auto);
						w = false;
					}
				}
			}
			/*
			for (String auto : automovel) {
				for (MarcaModel marcas : this.marcas) {
					System.out.println("anoAndModeloPorMarca ULTIMO FOR MARCAS");
					modelosAndAno = this.jsoupCrawler(urlApi + auto + "/marcas/" + marcas.getCodigo() + "/modelos");
					System.out.println("for modelo marca " + modelosAndAno);
					if (modelosAndAno != null) {
						allModelosAndAno = mapper.readValue(modelosAndAno, new TypeReference<Map<String, String>>() {
						});
						anos.addAll(mapper.readValue(allModelosAndAno.get("anos"), new TypeReference<Set<AnoModel>>() {
						}));
						modelos.addAll(mapper.readValue(allModelosAndAno.get("modelos"),
								new TypeReference<Set<ModeloModel>>() {
								}));
						System.out.println(
								"entrou no if " + allModelosAndAno.get("anos") + " " + allModelosAndAno.get("modelos"));
					}
				}
			}*/
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ex: parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/2014-3
	// Consulta ano ex:
	// parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos
	private void veiculoAndValor() {
		veiculoModel = new LinkedHashSet<>();
		this.veiculoModel = new LinkedHashSet<>();
		ObjectMapper mapper = new ObjectMapper();
		String urlConsulta;
		Set<AnoModel> anosConsulta = new LinkedHashSet<>();
		try {
			for (int i = 0; i > this.automovel.size(); i++) {
				for (MarcaModel marcas : this.marcas) {
					for (ModeloModel modelo : this.modelos) {
						String anoConsultaGet = this.jsoupCrawler(urlApi + this.automovel.get(i) + "/marcas/"
								+ marcas.getCodigo() + "/modelos/" + modelo.getCodigo() + "/anos");
						if (anoConsultaGet != null) {
							anosConsulta.addAll(mapper.readValue(anoConsultaGet, new TypeReference<Set<AnoModel>>() {
							}));
							VeiculoModel v = new VeiculoModel();
							Set<ValorModel> valorModel = new LinkedHashSet<>();

							for (AnoModel anos : anosConsulta) {
								System.out.println("veiculoAndValor ultimo for ANO");
								urlConsulta = this
										.jsoupCrawler(urlApi + this.automovel.get(i) + "/marcas/" + marcas.getCodigo()
												+ "/modelos/" + modelo.getCodigo() + "/anos/" + anos.getCodigo());
								if (urlConsulta != null) {
									Map<String, String> json = mapper.readValue(urlConsulta,
											new TypeReference<Map<String, String>>() {
											});
									valorModel.add(new ValorModel(anos, json.get("Valor"),
											Integer.parseInt(json.get("AnoModelo")), json.get("MesReferencia")));

									if (Objects.isNull(v)) {
										v.setMarca(marcas);
										v.setModelo(modelo);
										v.setTipoVeiculo(i + 1);
										v.setSiglaCombustivel(json.get("SiglaCombustivel"));
										v.setCombustivel(json.get("Combustivel"));
										v.setCodigoFipe(json.get("CodigoFipe"));
									}
									v.setValorModel(valorModel);
								}
							}
							this.veiculoModel.add(v);
						}
					}
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

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

	public void init() {
		this.todasAsMarcas();
		this.anoAndModeloPorMarca();
		// this.veiculoAndValor();
	}
}
