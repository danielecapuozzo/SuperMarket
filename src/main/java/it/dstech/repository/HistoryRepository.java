package it.dstech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.dstech.models.History;

public interface HistoryRepository extends CrudRepository<History, Integer> {

	List<History> findByCod(String cod);

}
