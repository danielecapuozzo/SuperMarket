package it.dstech.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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
import it.dstech.models.History;
import it.dstech.models.Prodotto;
import it.dstech.models.User;
import it.dstech.services.CarteDiCreditoService;
import it.dstech.services.HistoryService;
import it.dstech.services.ProdottoService;
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

	@Autowired
	private HistoryService historyService;

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
	public ResponseEntity<User> addProdotto(@PathVariable("prodottoid") int idProd, @PathVariable("carta") int idCarta,
			@PathVariable("quantitaDaAcquistare") double quantita) {
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

			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			logger.info("Formatter: " + formatter2);
			String date2 = prodotto.getDataDiScadenza();
			logger.info("Date: " + date2);

			LocalDate scadenzaProdotto = LocalDate.parse(date2, formatter2);
			logger.info("ScadenzaProdotto: " + scadenzaProdotto);

			int dayProd1 = (scadenzaProdotto.getDayOfMonth() - 1);
			int dayProd2 = (scadenzaProdotto.getDayOfMonth() - 2);
			int monthProd = scadenzaProdotto.getMonthValue();
			int yearProd = scadenzaProdotto.getYear();

			LocalDate scadProd = LocalDate.of(yearProd, monthProd, dayProd1);
			logger.info("scadProd: " + scadProd);

			LocalDate scadProd2 = LocalDate.of(yearProd, monthProd, dayProd2);
			logger.info("scadProd2: " + scadProd2);

			if (dNow.isEqual(scadenzaProdotto) || dNow.isEqual(scadProd) || dNow.isEqual(scadProd2)) {
				prodotto.setOfferta(prodotto.getPrezzoIvato() - (prodotto.getPrezzoIvato() * 0.40));
				logger.info("offerta" + prodotto.getOfferta());
			}

			if (prodotto.getQuantitaDisponibile() > 0
					&& prodotto.getQuantitaDaAcquistare() < prodotto.getQuantitaDisponibile()
					&& dNow.isBefore(scadenza)) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User user = userService.findByUsername(auth.getName());
				for (int i = 0; i < prodotto.getQuantitaDaAcquistare(); i++)
					user.getListaProdotti().add(prodSer.findById(idProd));
				user.setListaProdotti(user.getListaProdotti());
				logger.info("Lista prodotti user: " + user.getListaProdotti());
				logger.info("Lista prodotti user: " + prodSer.findById(idProd));
				prodotto.setQuantitaDisponibile(prodotto.getQuantitaDisponibile() - prodotto.getQuantitaDaAcquistare());
				prodSer.saveOrUpdateProdotto(prodotto);

				History history = new History();
				history.setNome(prodotto.getNome());
				history.setCategoria(prodotto.getCategoria());
				history.setMarca(prodotto.getMarca());
				history.setPrezzoIvato(prodotto.getPrezzoIvato());
				history.setUnita(prodotto.getUnita());
				historyService.saveHistory(history);

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
