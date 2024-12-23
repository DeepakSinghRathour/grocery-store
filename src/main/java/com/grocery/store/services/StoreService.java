package com.grocery.store.services;

import java.util.List;
import java.util.Optional;

import com.grocery.store.entities.Store;

public interface StoreService {
	
	/*
	 * Common Interfaces for both the members
	 */
	
	
	// get all item 
	List<Store> getAllItem();
	
	// get all item by id
	Optional<Store> getById(int n);
	
	/* 
	 *Interface for Admin 
	 */
	
	// add the items
	Store addItems(Store store);
	
	// update quantity
	String updateQuantity(int quantity, String itemName);
	
	// change price
	String updatePrice(int price, String itemName);
	
	/*
	 * Interface for user
	 */
	
	String totalPayableAmount(String itemName, int quantity);

}
