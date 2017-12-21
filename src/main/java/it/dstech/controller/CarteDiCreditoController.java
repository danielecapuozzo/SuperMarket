package it.dstech.controller;

import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

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
import it.dstech.models.History;
import it.dstech.models.User;
import it.dstech.services.CarteDiCreditoService;
import it.dstech.services.CarteDiCreditoServiceImpl;
import it.dstech.services.UserService;

@RestController
@RequestMapping("/carteDiCredito")
public class CarteDiCreditoController {

	private static final Logger logger = Logger.getLogger(CarteDiCreditoServiceImpl.class.getName());

	@Autowired
	private CarteDiCreditoService carteDiCreditoService;

	@Autowired
	private UserService userService;

	@GetMapping("/getModel")
	public CarteDiCredito getModel() {
		return new CarteDiCredito();
	}

	@PostMapping("/save")
	public ResponseEntity<CarteDiCredito> saveCarteDiCredito(@RequestBody CarteDiCredito carteDiCredito) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			carteDiCredito.setUser(user);
//			String num = carteDiCredito.getNumero();
//			String encodedString = Base64.getEncoder().encodeToString(num.getBytes());
//			carteDiCredito.setNumero(encodedString);
			CarteDiCredito saved = carteDiCreditoService.saveCarteDiCredito(carteDiCredito);
			userService.saveUser(user);
			logger.info("Saved: " + saved);
			return new ResponseEntity<CarteDiCredito>(saved, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info("Error: " + e);
			return new ResponseEntity<CarteDiCredito>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CarteDiCredito> deleteCarteDiCredito(@PathVariable int id) {
		try {
			carteDiCreditoService.deleteCarteDiCredito(id);
			return new ResponseEntity<CarteDiCredito>(HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error: " + e);
			return new ResponseEntity<CarteDiCredito>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findByUserId")
	public ResponseEntity<List<CarteDiCredito>> findIdByUser_Id() {
		try {	
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			int userid = userService.findByUsername(auth.getName()).getId();	
			List<CarteDiCredito> listFound =carteDiCreditoService.findIdByUser_id(userid);
			logger.info(listFound + "found by " + userid);
			return new ResponseEntity<List<CarteDiCredito>>(listFound, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error : " + e);
			return new ResponseEntity<List<CarteDiCredito>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<CarteDiCredito> findById(@PathVariable int id) {

		try {
			CarteDiCredito card = carteDiCreditoService.findById(id);
			return new ResponseEntity<CarteDiCredito>(card, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error : " + e);
			return new ResponseEntity<CarteDiCredito>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
