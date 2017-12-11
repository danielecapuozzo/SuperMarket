package it.dstech.services;

import java.util.List;

import it.dstech.models.CreditCard;

public interface CreditCardService {

	CreditCard saveCreditCard(CreditCard creditCard);
	
	void deleteCreditCard(int id);
	
	List<CreditCard> findByUser_id(int id);
	
	public CreditCard findById(int id);
	
}
