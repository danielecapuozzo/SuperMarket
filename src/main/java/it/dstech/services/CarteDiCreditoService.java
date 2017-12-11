package it.dstech.services;

import java.util.List;

import it.dstech.models.CarteDiCredito;

public interface CarteDiCreditoService {

	CarteDiCredito saveCarteDiCredito(CarteDiCredito carteDiCredito);
	
	void deleteCarteDiCredito(int id);
	
	List<CarteDiCredito> findByUser_id(int id);
	
	public CarteDiCredito findById(int id);
	
}
