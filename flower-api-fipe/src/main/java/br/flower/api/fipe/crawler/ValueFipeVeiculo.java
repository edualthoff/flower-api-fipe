package br.flower.api.fipe.crawler;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import br.flower.api.fipe.crawler.model.VeiculoModel;

public interface ValueFipeVeiculo {

	public CompletableFuture<Set<VeiculoModel>> listVeiculo();
	public Set<VeiculoModel> listSetVeiculo();
	public void init();
	
}
