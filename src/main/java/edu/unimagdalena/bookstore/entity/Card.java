package edu.unimagdalena.bookstore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Card {

	@Id
	@Column(unique = true, nullable = false, length = 32)
	private String number;

	@Column(unique = true, nullable = false, length = 4)
	private String cvv;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private String emisor;

	@Column(nullable = false)
	private Date expires_at;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "card")
	@JsonManagedReference
	private Order order;

	public Card() {
		// TODO Auto-generated constructor stub
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public Date getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(Date expires_at) {
		this.expires_at = expires_at;
	}

}
