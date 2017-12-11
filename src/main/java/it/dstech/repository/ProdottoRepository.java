package it.dstech.repository;

import org.springframework.data.repository.CrudRepository;
import it.dstech.models.Categoria;
import it.dstech.models.Prodotto;

public interface ProdottoRepository extends CrudRepository<Prodotto, Integer> {

	Prodotto findById(int id);
		
	Prodotto findByCategoria (Categoria categoria);
	
}
