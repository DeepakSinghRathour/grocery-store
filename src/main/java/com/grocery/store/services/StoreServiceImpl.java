package com.grocery.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.store.entities.Store;
import com.grocery.store.repository.StoreRepository;


@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private QueryService queryService;
	
	@Override
	public List<Store> getAllItem() {
		return storeRepository.findAll();
	}

	@Override
	public Optional<Store> getById(int itemId) {
		return storeRepository.findById(itemId);
	}
	
	@Override
	public Store addItems(Store store) {
		return storeRepository.save(store);
	}

	@Override
	public String updateQuantity(int quantity, String itemName) {
		return queryService.updateQuantity(quantity, itemName);
	}

	@Override
	public String updatePrice(int price, String itemName) {
		return queryService.updatePrice(price, itemName);
	}

	@Override
	public String totalPayableAmount(String itemName, int quantity) {
		return queryService.totalPayableAmount(itemName, quantity);
	}

}
