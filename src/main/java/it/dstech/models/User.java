package it.dstech.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class User {

	@Id
	@GeneratedValue
	private int id;

	private String username;

	private String password;

	private RoleUser role;

	private TipoUtente tipo;

	private String tel;

	private String via;

	private String cap;

	private List<CarteDiCredito> cartaCredito;

	public User(int id, String username, String password, RoleUser role, TipoUtente tipo, String tel, String via,
			String cap, List<CarteDiCredito> cartaCredito) {

		this.cartaCredito = new ArrayList<>();

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

	public RoleUser getRole() {
		return role;
	}

	public void setRole(RoleUser role) {
		this.role = role;
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

	public List<CarteDiCredito> getCartaCredito() {
		return cartaCredito;
	}

	public void setCartaCredito(List<CarteDiCredito> cartaCredito) {
		this.cartaCredito = cartaCredito;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", tipo="
				+ tipo + ", tel=" + tel + ", via=" + via + ", cap=" + cap + ", cartaCredito=" + cartaCredito + "]";
	}

}
