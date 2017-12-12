package it.dstech.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.dstech.models.History;
import it.dstech.repository.HistoryRepository;

public class HistoryServiceImpl implements HistoryService{
	

	@Autowired
	HistoryRepository historyRepository;
	

	@Override
	public List<History> findAll() {
		return (List<History>) historyRepository.findAll();

	}

	
}
