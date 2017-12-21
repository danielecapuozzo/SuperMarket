package it.dstech.services;

import java.util.List;

import it.dstech.models.Categoria;
import it.dstech.models.Prodotto;

public interface ProdottoService {

	Prodotto saveOrUpdateProdotto(Prodotto prodotto);
	
	List<Prodotto> findAll();
	
	List<Prodotto> findByCategoria(Categoria categoria);
	
	List<Prodotto> findByQuantitaDisponibileGreaterThan(double quantita);
	
	void deleteProdotto(int id);
	
	public Prodotto findById(int id);
	
	List<Prodotto> findByNome (String nome);
	
}
