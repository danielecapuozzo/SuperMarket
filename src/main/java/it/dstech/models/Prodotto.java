package it.dstech.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Prodotto {

	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
	
	private String marca;
	
	private LocalDate dataDiScadenza;
	
	private Categoria categoria;
	
	private double quantitaDisponibile;

	private double quantitaDaAcquistare;
	
	private Unita unita;
	
	private double prezzoUnitario;

	private double prezzoSenzaIva;

	private double prezzoIvato;
	
	private String img;
	
	private int offerta;
	
	private User user;

	public Prodotto() {
	}

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

	public LocalDate getDataDiScadenza() {
		return dataDiScadenza;
	}

	public void setDataDiScadenza(LocalDate dataDiScadenza) {
		this.dataDiScadenza = dataDiScadenza;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getQuantitaDisponibile() {
		return quantitaDisponibile;
	}

	public void setQuantitaDisponibile(double quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}

	public double getQuantitaDaAcquistare() {
		return quantitaDaAcquistare;
	}

	public void setQuantitaDaAcquistare(double quantitaDaAcquistare) {
		this.quantitaDaAcquistare = quantitaDaAcquistare;
	}

	public Unita getUnita() {
		return unita;
	}

	public void setUnita(Unita unita) {
		this.unita = unita;
	}

	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public double getPrezzoSenzaIva() {
		return prezzoSenzaIva;
	}

	public void setPrezzoSenzaIva(double prezzoSenzaIva) {
		this.prezzoSenzaIva = prezzoSenzaIva;
	}

	public double getPrezzoIvato() {
		return prezzoIvato;
	}

	public void setPrezzoIvato(double prezzoIvato) {
		this.prezzoIvato = prezzoIvato;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getOfferta() {
		return offerta;
	}

	public void setOfferta(int offerta) {
		this.offerta = offerta;
	}

	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", nome=" + nome + ", marca=" + marca + ", quantitaDisponibile="
				+ quantitaDisponibile + ", quantitaDaAcquistare=" + quantitaDaAcquistare + ", prezzoUnitario="
				+ prezzoUnitario + ", prezzoSenzaIva=" + prezzoSenzaIva + ", prezzoIvato=" + prezzoIvato + ", img="
				+ img + ", offerta=" + offerta + "]";
	}
	
}
