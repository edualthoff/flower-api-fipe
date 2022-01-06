package br.flower.api.fipe.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/scan")
public class TabelaFipeController {
	private static final Logger log = LoggerFactory.getLogger(TabelaFipeController.class);

	@Autowired
	private TabelaFileCrawlerStart tabelaFileCrawlerStart;
	
	@GetMapping(name = "/fipe")
	public String atualizarFipe() {
		log.debug("Atualizacao iniciada");
		this.tabelaFileCrawlerStart.start();
		log.debug("Atualizacao finalizada");
		return "Start";
	}

}
