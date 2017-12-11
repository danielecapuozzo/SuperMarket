package it.dstech.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CarteDiCredito {

	@Id
	@GeneratedValue
	private int id;

	private String numero;

	private LocalDate scadenza;

	private String ccv;

	private double credito;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	private User user;

	public CarteDiCredito() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getScadenza() {
		return scadenza;
	}

	public void setScadenza(LocalDate scadenza) {
		this.scadenza = scadenza;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public double getCredito() {
		return credito;
	}

	public void setCredito(double credito) {
		this.credito = credito;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CarteDiCredito [id=" + id + ", numero=" + numero + ", scadenza=" + scadenza + ", ccv=" + ccv
				+ ", credito=" + credito + ", user=" + user + "]";
	}

}

