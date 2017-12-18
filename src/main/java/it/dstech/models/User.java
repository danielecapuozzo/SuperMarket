package it.dstech.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue
	private int id;

	@Column(unique = true)
	private String username;

	private String password;

	@Enumerated(EnumType.STRING)
	private TipoUtente tipo;

	private String tel;

	private String via;

	private String cap;

	@Enumerated(EnumType.STRING)
	private UserProfileType profileType;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<History> listaHistory;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "USER_PRODJ", joinColumns = @JoinColumn(name = "USERJ_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PRODJ_ID", referencedColumnName = "ID"))
	private List<Prodotto> listaProdotti;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<CarteDiCredito> carteDiCredito;

	public User() {

	}

	public User(String username, String password, TipoUtente tipo, String tel, String via, String cap,
			UserProfileType profileType) {
		this.listaProdotti = new ArrayList<>();
		this.carteDiCredito = new ArrayList<>();
		this.username = username;
		this.password = password;
		this.tipo = tipo;
		this.tel = tel;
		this.via = via;
		this.cap = cap;
		this.profileType = profileType;
	}

	public UserProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(UserProfileType profileType) {
		this.profileType = profileType;
	}

	public List<CarteDiCredito> getCarteDiCredito() {
		return carteDiCredito;
	}

	public void setCarteDiCredito(List<CarteDiCredito> carteDiCredito) {
		this.carteDiCredito = carteDiCredito;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoUtente getTipo() {
		return tipo;
	}

	public void setTipo(TipoUtente tipo) {
		this.tipo = tipo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public List<Prodotto> getListaProdotti() {
		return listaProdotti;
	}

	public void setListaProdotti(List<Prodotto> listaProdotti) {
		this.listaProdotti = listaProdotti;
	}

	public List<CarteDiCredito> getCartaCredito() {
		return carteDiCredito;
	}

	public void setCartaCredito(List<CarteDiCredito> cartaCredito) {
		this.carteDiCredito = cartaCredito;
	}

	public List<History> getListaHistory() {
		return listaHistory;
	}

	public void setListaHistory(List<History> listaHistory) {
		this.listaHistory = listaHistory;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", tipo=" + tipo + ", tel=" + tel + ", via="
				+ via + ", cap=" + cap + ", profileType=" + profileType + "]";
	}

}
