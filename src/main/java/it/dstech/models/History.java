package it.dstech.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class History {

	@Id
	@GeneratedValue
	private int id;

	private String cod;
	
	private int prezzoTotale;

	private String data;
	
	@JsonIgnore
	@OneToMany(mappedBy = "listaProdotti")
	List<Prodotto> listaProdotti;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	public int getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(int prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public List<Prodotto> getListaProdotti() {
		return listaProdotti;
	}

	public void setListaProdotti(List<Prodotto> listaProdotti) {
		this.listaProdotti = listaProdotti;
	}

	public History() {
	}

	public History(int id, String cod, int prezzoTotale, String data, List<Prodotto> listaProdotti) {
		super();
		this.id = id;
		this.cod = cod;
		this.prezzoTotale = prezzoTotale;
		this.data = data;
		this.listaProdotti = listaProdotti;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", cod=" + cod + ", prezzoTotale=" + prezzoTotale + ", data=" + data
				+ ", listaProdotti=" + listaProdotti + "]";
	}

	
}
