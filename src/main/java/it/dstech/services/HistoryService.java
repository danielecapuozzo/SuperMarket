package it.dstech.services;

import java.util.List;

import it.dstech.models.History;
import it.dstech.models.Prodotto;

public interface HistoryService {

	List<History> findAll();

	History saveHistory(History history);

	List<History> findByCod(String cod);
	
	List<History>  findCodByUser_id(int id);

	History findById(int id);
	



}
