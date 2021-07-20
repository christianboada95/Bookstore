package edu.unimagdalena.bookstore.model;

import edu.unimagdalena.bookstore.entity.OrderDetail;
import edu.unimagdalena.bookstore.entity.users.Customer;

public class OrderModel {
	
	public Customer client;
	
	//public Date date;
	
	public Integer total;
	
	//correo normal, expreso, internacional o courier
	public String shipping;
	
	public OrderDetail[] details;

	public OrderModel() {
		// TODO Auto-generated constructor stub
	}
	
}
