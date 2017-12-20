package it.dstech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.dstech.models.History;
import it.dstech.models.Prodotto;

public interface HistoryRepository extends CrudRepository<History, Integer> {

	List<History> findByCod(String cod);
	
	List<History> findCodByUser_id(int id);

	History findById(int id);
	

	
}
