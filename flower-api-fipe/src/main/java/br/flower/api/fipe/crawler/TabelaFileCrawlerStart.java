package br.flower.api.fipe.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TabelaFileCrawlerStart {
	private static final Logger log = LoggerFactory.getLogger(TabelaFileCrawlerStart.class);

	@Autowired
	private TabelaFipeSave tabelaFipeSave;
	@Autowired
	private TabelaFipeScan tabelaFipeScan;
	
	
	public void start() {
		log.debug("start - segunda thread");
		tabelaFipeSave.saveAndUpdadeFipe();
		log.debug("start - primeira thread");
		this.tabelaFipeScan.scanMountFipe();
	}
}
