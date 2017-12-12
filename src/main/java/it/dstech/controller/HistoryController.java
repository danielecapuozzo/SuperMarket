package it.dstech.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.models.History;
import it.dstech.services.HistoryService;

@RestController
@RequestMapping("/history")
public class HistoryController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private HistoryService historyservice;
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<History>> findAll() {
		try {
			List<History> listaHistory = historyservice.findAll();
			if (!listaHistory.isEmpty()) {
				logger.info("lista; ");
				return new ResponseEntity<List<History>>(listaHistory, HttpStatus.OK);
			} else
				return new ResponseEntity<List<History>>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<List<History>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
