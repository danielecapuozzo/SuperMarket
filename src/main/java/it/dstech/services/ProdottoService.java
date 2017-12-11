package it.dstech.services;

import java.util.List;

import it.dstech.models.Prodotto;

public interface ProdottoService {

	Prodotto saveOrUpdateProdotto(Prodotto prodotto);
	
	List<Prodotto> findAll();
	
	List<Prodotto> findByCategoria(String categoria);
	
	List<Prodotto> findAllDisponibili();
	
	void deleteProdotto(int id);
	
	public Prodotto findById(int id);
	
	public List<Prodotto> findByUserId(int id);
	
}
