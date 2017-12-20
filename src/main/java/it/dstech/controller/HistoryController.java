package it.dstech.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.models.CarteDiCredito;
import it.dstech.models.History;
import it.dstech.models.Prodotto;
import it.dstech.models.User;
import it.dstech.services.HistoryService;
import it.dstech.services.UserService;

@RestController
@RequestMapping("/history")
public class HistoryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HistoryService historyService;

	@Autowired
	private UserService userService;

	@GetMapping("/findAll")
	public ResponseEntity<List<History>> findAll() {
		try {
			List<History> listaHistory = historyService.findAll();
			if (!listaHistory.isEmpty()) {
				return new ResponseEntity<List<History>>(listaHistory, HttpStatus.OK);
			} else
				return new ResponseEntity<List<History>>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<List<History>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findByCod/{cod}")
	public ResponseEntity<List<History>> findByCod(@PathVariable String cod) {

		try {
			List<History> listFound0 = historyService.findByCod(cod);
			logger.info(listFound0 + "found by " + cod);
			return new ResponseEntity<List<History>>(listFound0, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error : " + e);
			return new ResponseEntity<List<History>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<History> saveHistoy(@RequestBody History history) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			history.setUser(user);
			History saved = historyService.saveHistory(history);
			logger.info("Saved: " + saved);
			return new ResponseEntity<History>(saved, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info("Error: " + e);
			return new ResponseEntity<History>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findCodByUserId")
	public ResponseEntity<List<History>> findCodByUser_id() {

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			int userid = userService.findByUsername(auth.getName()).getId();
			List<History> listFound = historyService.findCodByUser_id(userid);
			logger.info(listFound + "found by " + userid);
			return new ResponseEntity<List<History>>(listFound, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error : " + e);
			return new ResponseEntity<List<History>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<History> findById(@PathVariable int id) {
		try {
			History found = historyService.findById(id);
			if (found != null) {
				logger.info("Model; " + found);
				return new ResponseEntity<History>(found, HttpStatus.OK);
			} else
				return new ResponseEntity<History>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<History>(HttpStatus.NOT_FOUND);
		}
	}

//	@GetMapping("/findList/{id}")
//	public ResponseEntity<List<Prodotto>> findByListaProdotti_id(@PathVariable int id) {
//		try {
//			List<Prodotto> listFound0 = historyService.findByListaProdotti_id(id);
//			logger.info(listFound0 + "found by " + id);
//			return new ResponseEntity<List<Prodotto>>(listFound0, HttpStatus.OK);
//		} catch (Exception e) {
//			logger.error("Error : " + e);
//			return new ResponseEntity<List<Prodotto>>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


}
