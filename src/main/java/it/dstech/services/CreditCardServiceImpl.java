package it.dstech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.CreditCard;
import it.dstech.repository.CreditCardRepository;
@Service
public class CreditCardServiceImpl implements CreditCardService{

	@Autowired
	CreditCardRepository creditCardRepository;
	
	@Override
	public CreditCard saveCreditCard(CreditCard creditCard) {
		return creditCardRepository.save(creditCard);
	}

	@Override
	public void deleteCreditCard(int id) {
		creditCardRepository.delete(creditCardRepository.findOne(id));;
	}

	@Override
	public List<CreditCard> findByUser_id(int id) {
		return creditCardRepository.findByUser_id(id);
	}
	
	@Override
	public CreditCard findById(int id) {
		return creditCardRepository.findById(id);
	}

}
