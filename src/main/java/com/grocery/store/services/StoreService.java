package com.grocery.store.services;

import java.util.List;
import java.util.Optional;

import com.grocery.store.entities.Store;

public interface StoreService {
	
	// get all item 
	List<Store> getAllItem();
	
	// get all item by id
	Optional<Store> getById(int n);
	
	// add the items
	Store addItems(Store store);
	
	// update quantity
	int updateQuantity(int quantity, String itemName);
	
	// change price
	int updatePrice(int price, String itemName);
}
