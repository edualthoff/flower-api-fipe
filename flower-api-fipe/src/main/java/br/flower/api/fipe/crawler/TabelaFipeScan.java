package br.flower.api.fipe.crawler;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.flower.api.fipe.crawler.model.VeiculoModel;
import br.flower.api.fipe.crawler.parallelum.ParallelumCrawler;

@Service
public class TabelaFipeScan {
	private static final Logger log = LoggerFactory.getLogger(TabelaFipeScan.class);

	static Set<VeiculoModel> listVeiculoModel;
	//CompletableFuture<Set<VeiculoModel>> listVeiculoFipe;

	
	
	@Async("threadPoolTaskExecutor")
	public void scanMountFipe() {
		log.debug("scanMountFipe - " +Thread.currentThread().getName());
		ValueFipeVeiculo sacanWebcralerVeiculo = new ParallelumCrawler();
		listVeiculoModel = sacanWebcralerVeiculo.listSetVeiculo();
		sacanWebcralerVeiculo.init();
	}
	
	
	
}
