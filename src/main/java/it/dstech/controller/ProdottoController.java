package it.dstech.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dstech.models.CarteDiCredito;
import it.dstech.models.Categoria;
import it.dstech.models.Prodotto;
import it.dstech.models.User;
import it.dstech.services.CarteDiCreditoService;
import it.dstech.services.ProdottoService;
import it.dstech.services.ProdottoServiceImpl;
import it.dstech.services.UserService;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {

	@Autowired
	private ProdottoService prodSer;

	@Autowired
	private UserService userService;

	@Autowired
	private CarteDiCreditoService cardService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/getModel")
	public Prodotto getModel() {
		return new Prodotto();
	}

	@PostMapping("/saveOrUpdateProdotto")
	public ResponseEntity<Prodotto> saveOrUpdateProdotto(@RequestBody Prodotto prodotto) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			userService.saveUser(user);
			prodotto.setPrezzoSenzaIva(prodotto.getPrezzoIvato() - (prodotto.getPrezzoIvato() * 0.22));
			Prodotto saved = prodSer.saveOrUpdateProdotto(prodotto);
			logger.info("saved; " + saved);
			return new ResponseEntity<Prodotto>(saved, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error " + e);
			return new ResponseEntity<Prodotto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Prodotto>> findAll() {
		try {
			List<Prodotto> listaProdotti = prodSer.findAll();
			if (!listaProdotti.isEmpty()) {
				logger.info("lista; ");
				return new ResponseEntity<List<Prodotto>>(listaProdotti, HttpStatus.OK);
			} else
				return new ResponseEntity<List<Prodotto>>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<List<Prodotto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteProdotto/{id}")
	public void deleteProdotto(@PathVariable int id) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			userService.saveUser(user);
			logger.info("delete: " + id);
			prodSer.deleteProdotto(id);
		} catch (Exception e) {

		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Prodotto> findById(@PathVariable int id) {
		try {
			Prodotto found = prodSer.findById(id);
			if (found != null) {
				logger.info("Model; " + found);
				return new ResponseEntity<Prodotto>(found, HttpStatus.OK);
			} else
				return new ResponseEntity<Prodotto>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<Prodotto>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findByCategoria/{categoria}")
	public ResponseEntity<List<Prodotto>> findByCategoria(@PathVariable Categoria categoria) {
		try {
			List<Prodotto> listFound = prodSer.findByCategoria(categoria);
			if (listFound != null) {
				logger.info("Model; " + listFound);
				return new ResponseEntity<List<Prodotto>>(listFound, HttpStatus.OK);
			} else
				return new ResponseEntity<List<Prodotto>>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<List<Prodotto>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findByQuantitaDisponibileGreaterThan")
	public ResponseEntity<List<Prodotto>> findByQuantitaDisponibileGreaterThan() {
		try {

			List<Prodotto> listFound = prodSer.findByQuantitaDisponibileGreaterThan(0);

			logger.info("Model; " + listFound);
			return new ResponseEntity<List<Prodotto>>(listFound, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Prodotto>>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addprodotto/{prodottoid}/{carta}/{quantitaDaAcquistare}")
	public ResponseEntity<User> addProdotto(@PathVariable("prodottoid") int idProd,
			@PathVariable("carta") int idCarta,@PathVariable("quantitaDaAcquistare") double quantita) {
		try {
			
			CarteDiCredito card = cardService.findById(idCarta);
			logger.info("Id della carta: " + idCarta);
			Prodotto prodotto = prodSer.findById(idProd);
			prodotto.setQuantitaDaAcquistare(quantita);
			logger.info("Id del prodotto: " + idProd);
			LocalDate dNow = LocalDate.now();
			logger.info("Anno: " + dNow);
			// -----
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
			logger.info("Formatter: " + formatter);
			String date = card.getScadenza();
			logger.info("Date: " + date);
			YearMonth scadenzaMese = YearMonth.parse(date, formatter);
			logger.info("ScadenzaMese: " + scadenzaMese);
			LocalDate scadenza = scadenzaMese.atEndOfMonth();
			logger.info("Scadenza: " + scadenza);

			// -----
			logger.info("If 1: " + prodotto.getQuantitaDisponibile());
			logger.info("If 2" + dNow.isBefore(scadenza));
			if (prodotto.getQuantitaDisponibile() > 0
					&& prodotto.getQuantitaDaAcquistare() < prodotto.getQuantitaDisponibile()
					&& dNow.isBefore(scadenza)) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User user = userService.findByUsername(auth.getName());
				for(int i=0; i<prodotto.getQuantitaDaAcquistare();i++) 
				user.getListaProdotti().add(prodSer.findById(idProd));
				user.setListaProdotti(user.getListaProdotti());
				logger.info("Lista prodotti user: " + user.getListaProdotti());
				logger.info("Lista prodotti user: " + prodSer.findById(idProd));
				prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() - prodotto.getQuantitaDaAcquistare());
				prodSer.saveOrUpdateProdotto(prodotto);
				userService.saveUser(user);
				// ------
				cardService.saveCarteDiCredito(card);
				// ---------
				return new ResponseEntity<User>(HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
