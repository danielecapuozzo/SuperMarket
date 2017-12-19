package it.dstech.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class History {

	@Id
	@GeneratedValue
	private int id;

	private String nome;

	private String marca;

	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@Enumerated(EnumType.STRING)
	private Unita unita;

	private double prezzoIvato;

	private String cod;

	private double quantita;

	private String data;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Unita getUnita() {
		return unita;
	}

	public void setUnita(Unita unita) {
		this.unita = unita;
	}

	public double getPrezzoIvato() {
		return prezzoIvato;
	}

	public void setPrezzoIvato(double prezzoIvato) {
		this.prezzoIvato = prezzoIvato;
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

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public History(int id, String nome, String marca, Categoria categoria, Unita unita, double prezzoIvato, User user) {
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.categoria = categoria;
		this.unita = unita;
		this.prezzoIvato = prezzoIvato;
		this.user = user;
	}

	public History() {
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", nome=" + nome + ", marca=" + marca + ", categoria=" + categoria + ", unita="
				+ unita + ", prezzoIvato=" + prezzoIvato + ", user=" + user + "]";
	}

}
