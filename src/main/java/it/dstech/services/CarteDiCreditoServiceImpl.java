package it.dstech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.CarteDiCredito;
import it.dstech.repository.CarteDiCreditoRepository;
@Service
public class CarteDiCreditoServiceImpl implements CarteDiCreditoService{

	@Autowired
	CarteDiCreditoRepository carteDiCreditoRepository;
	
	@Override
	public CarteDiCredito saveCarteDiCredito(CarteDiCredito carteDiCredito) {
		return carteDiCreditoRepository.save(carteDiCredito);
	}

	@Override
	public void deleteCarteDiCredito(int id) {
		carteDiCreditoRepository.delete(carteDiCreditoRepository.findOne(id));;
	}

	@Override
	public List<CarteDiCredito> findIdByUser_id(int id) {
		return carteDiCreditoRepository.findIdByUser_id(id);
	}
	
	@Override
	public CarteDiCredito findById(int id) {
		return carteDiCreditoRepository.findById(id);
	}




}
