package it.dstech.repository;

import org.springframework.data.repository.CrudRepository;

import it.dstech.models.History;

public interface HistoryRepository extends CrudRepository<History, Integer> {

}
