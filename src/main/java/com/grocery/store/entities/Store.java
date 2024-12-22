package com.grocery.store.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Store")
public class Store {
	public Store() {
		
	}
	
	@Id
	private int ItemId;
	
	@Column(name="NameOfItem", nullable=false, length=512)
	private String ItemName;
	
	@Column(name="Quantity")
	private int quantity;
	
	@Column(name="Price")
	private double price;
	
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		ItemId = itemId;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Store(int itemId, String itemName, int quantity, double price) {
		super();
		ItemId = itemId;
		ItemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}
}
