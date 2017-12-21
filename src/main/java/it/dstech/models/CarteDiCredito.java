package it.dstech.models;

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

	private String scadenza;

	private String ccv;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
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

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public CarteDiCredito(String numero, String scadenza, String ccv) {
		this.numero = numero;
		this.scadenza = scadenza;
		this.ccv = ccv;
	}

	@Override
	public String toString() {
		return "CarteDiCredito [numero=" + numero + ", scadenza=" + scadenza + ", ccv=" + ccv + "]";
	}

}