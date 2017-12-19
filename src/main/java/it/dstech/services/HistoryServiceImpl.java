package it.dstech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.models.History;
import it.dstech.models.Prodotto;
import it.dstech.repository.HistoryRepository;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	HistoryRepository historyRepository;

	@Override
	public List<History> findAll() {
		return (List<History>) historyRepository.findAll();

	}

	@Override
	public History saveHistory(History history) {
		return historyRepository.save(history);
	}

	@Override
	public List<History> findByCod(String cod) {
		return historyRepository.findByCod(cod);
	}

}
