package br.flower.api.fipe.crawler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.flower.api.fipe.ano.Ano;
import br.flower.api.fipe.ano.AnoRepository;

@Service
public class CorrigirAno {

	@Autowired
	private AnoRepository anoRepository;
	
	public void corrigirAno() {
		List<Ano> anos = this.anoRepository.findAll();
		List<Ano> anosNews = new ArrayList<Ano>();
		
		for(Ano a : anos) {
			String[] linha = a.getNome().trim().split("-");
			if(linha[1].equalsIgnoreCase("1")) {
				a.setNome(linha[0]+" Gasolina");
			} if(linha[1].equalsIgnoreCase("2")) {
				a.setNome(linha[0]+" Álcool");
			}  if(linha[1].equalsIgnoreCase("3")) {
				a.setNome(linha[0]+" Diesel");
			}
			anosNews.add(a);
			System.out.println("e "+linha[1]);
		}
		anoRepository.saveAll(anosNews);
	}
}
