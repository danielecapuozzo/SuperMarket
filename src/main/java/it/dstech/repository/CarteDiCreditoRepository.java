package it.dstech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.dstech.models.CarteDiCredito;

public interface CarteDiCreditoRepository extends CrudRepository<CarteDiCredito, Integer> {

	List<CarteDiCredito> findIdByUser_id(int id);

	CarteDiCredito findById(int id);

}
