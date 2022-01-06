package br.flower.api.fipe.crawler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.crawler.model.ValorModel;
import br.flower.api.fipe.crawler.model.VeiculoModel;
import br.flower.api.fipe.marca.Marca;
import br.flower.api.fipe.mes.referencia.MesReferencia;
import br.flower.api.fipe.modelo.Modelo;
import br.flower.api.fipe.valor.ValorVeiculo;
import br.flower.api.fipe.veiculo.TipoVeiculo;
import br.flower.api.fipe.veiculo.Veiculo;
import br.flower.api.fipe.veiculo.VeiculoServiceImp;
import br.flower.api.fipe.veiculo.caminhao.Caminhao;
import br.flower.api.fipe.veiculo.carro.Carro;
import br.flower.api.fipe.veiculo.moto.Moto;

/**
 * Arrumar a parte de salvar no BANCO DE DADOS - CLASS AUTOMOVEL OU CLASS VALOR AUTOMOVEL
 * @author edu
 *
 */
@Service
public class TabelaFipeSave {
	private static final Logger log = LoggerFactory.getLogger(TabelaFipeSave.class);

	@Autowired
	private VeiculoServiceImp veiculoRepository;

	private static DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder().appendPattern("MMMM 'de' uuuu")
			.parseStrict().parseCaseInsensitive().parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
			.toFormatter(new Locale("pt", "BR"));

	@Async("threadPoolTaskExecutor")
	//@Transactional
	public void saveAndUpdadeFipe() {
		log.debug("Entrou na Thread de Salvar no BD");
		boolean loop = true;
		int count = 0;
		do {

			if (TabelaFipeScan.listVeiculoModel == null || TabelaFipeScan.listVeiculoModel.isEmpty()) {
				System.out.println("saveAndUpdadeFipe  entrou loop count: " + count);
				try {
					count++;
					if (count == 10) {
						loop = false;
					} else {
						log.debug("saveAndUpdadeFipe - " + Thread.currentThread().getName());
						Thread.sleep(5000L);
					}
				} catch (InterruptedException e) {
					log.error("saveAndUpdadeFipe - Erro sleep thread - if");
					e.printStackTrace();
				}
			} else {
				count = 0;
				Set<VeiculoModel> veiculoModelList = new LinkedHashSet<VeiculoModel>();
				veiculoModelList.addAll(TabelaFipeScan.listVeiculoModel);
				Set<Veiculo> listVeiculo = new LinkedHashSet<>();
				for(VeiculoModel ve : veiculoModelList) {
					//VeiculoModel ve = veiculoModelList.iterator().next();
					for (ValorModel anoM : ve.getValorModel()) {
						Veiculo veiculo = null;
						TipoVeiculo tipo = TipoVeiculo.setVeiculo(ve.getTipoVeiculo());
						//System.out.println("saveAndUpdadeFipe - For Valor Model - Ano "+tipo.getValue());
						//System.out.println("tipo veiculo BD - " + tipo + " " + tipo.getValue() + " site: " + ve.getTipoVeiculo() + " modelo: " + ve.getModelo().getNome());
						
						switch (tipo) {
						case CARRO:
							veiculo = new Carro();
							break;
						case MOTO:
							veiculo = new Moto();
							break;
						case CAMINHAO:
							veiculo = new Caminhao();
							break;
						}
						Marca m = new Marca(Long.parseLong(ve.getMarca().getCodigo()), ve.getMarca().getNome());
						Modelo modelo = new Modelo(ve.getModelo().getCodigo(), ve.getModelo().getNome(),
								ve.getCodigoFipe(), ve.getCombustivel(), ve.getSiglaCombustivel());

						veiculo.setMarca(m);
						veiculo.setModelo(modelo);
						//veiculo.setValorVeiculos(new LinkedHashSet<ValorVeiculo>());

						// Set<ValorVeiculo> listValor = new LinkedHashSet<>();
						veiculo.setVeiculoPk(new Veiculo.VeiculoPk(anoM.getAno().getCodigo(),
								veiculo.getMarca().getMarcaId(), veiculo.getModelo().getModeloId()));
						veiculo.setAnoModelo(anoM.getAnoModelo());

						Ano ano = new Ano(anoM.getAno().getCodigo(), anoM.getAno().getCodigo());
						veiculo.setAnoVeiculo(ano);

						MesReferencia mes = new MesReferencia(
								LocalDate.parse(anoM.getMesReferencia().trim(), DATE_FORMAT));
						// System.out.println("date:
						// "+LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM 'de' yyyy", new
						// Locale("pt", "BR"))));
						// DateTimeFormatter.ofPattern("MMMM 'de' uuuu", new Locale("pt",
						// "BR")).withResolverStyle(ResolverStyle.STRICT)

						// ValorVeiculo valorVeiculo = new ValorVeiculo(anoM.getValor(), mes);
						// valorVeiculo.setVeiculo(veiculo);
						// listValor.add(valorVeiculo);
						//veiculo.getValorVeiculos().add(new ValorVeiculo(veiculo.getVeiculoPk(), mes.getMesId(), anoM.getValor(), mes, veiculo));
						listVeiculo.add(veiculo);
					}

				}
				log.debug("Pronto para salvar size: "+listVeiculo.size());
				this.veiculoRepository.saveAll(listVeiculo);
				TabelaFipeScan.listVeiculoModel.removeAll(veiculoModelList);
				listVeiculo.clear();
				veiculoModelList.clear();
				log.debug("Lista de veiculos Scan: "+TabelaFipeScan.listVeiculoModel.size());
				try {
					Thread.sleep(30000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} while (loop == true);
	}
}
