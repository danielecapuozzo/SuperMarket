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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.dstech.models.History;
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
				logger.info("lista; ");
				return new ResponseEntity<List<History>>(listaHistory, HttpStatus.OK);
			} else
				return new ResponseEntity<List<History>>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
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

}
