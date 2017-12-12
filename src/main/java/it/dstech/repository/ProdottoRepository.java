package it.dstech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.dstech.models.Categoria;
import it.dstech.models.Prodotto;

public interface ProdottoRepository extends CrudRepository<Prodotto, Integer> {

	
	Prodotto findById(int id);
		
	List<Prodotto> findByCategoria (Categoria categoria);
	
	List<Prodotto> findByQuantitaDisponibileGreaterThan(double quantita);
	
}
