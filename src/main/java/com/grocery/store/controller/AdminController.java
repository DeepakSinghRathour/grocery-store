package com.grocery.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.store.entities.Store;
import com.grocery.store.services.StoreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/admin")
@Tag(name="Store", description="APIs related to store operations")
public class AdminController {

	@Autowired
	private StoreService storeService;
	
	@GetMapping("/allItem")
	@Operation(summary = "Get all items",
			   description = "Get all items with details",
			   responses = {
					   @ApiResponse(responseCode = "200", description = "Item found"),
					   @ApiResponse(responseCode = "404", description = "Item Not Found")
	})
	public ResponseEntity<List<Store>> getAllItem(){
		List<Store> list = storeService.getAllItem();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}
	
	@GetMapping("/allItem/{id}")
	@Operation(summary = "Get all items by id",
			   description = "Get all items with details by their id",
			   responses = {
					   @ApiResponse(responseCode = "200", description = "Item found"),
					   @ApiResponse(responseCode = "404", description = "Item Not Found")
	})
	public ResponseEntity<Optional<Store>> getById(@PathVariable int id){
		Optional<Store> store = storeService.getById(id);
		if(store == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(store));
	}
	
	@PostMapping("/addItem")
	@Operation(summary = "Add the item",
	   description = "Add any item in the list",
	   responses = {
			   @ApiResponse(responseCode = "200", description = "Item found")
	})
	public ResponseEntity<Store> addItem(@RequestBody Store store) {
		storeService.addItems(store);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@PostMapping("/addQuanity/{itemName}/{quantity}")
	@Operation(summary = "Update quantity",
	   description = "Please update the quantity of the product",
	   responses = {
			   @ApiResponse(responseCode = "200", description = "Updated"),
			   @ApiResponse(responseCode = "404", description = "Please enter a valid number")
	})
	public ResponseEntity<HttpStatus> updateQuantity(@PathVariable int quantity, @PathVariable String itemName) {
		int result = storeService.updateQuantity(quantity, itemName);
		if(result!=0) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping("/addPrice/{itemName}/{price}")
	@Operation(summary = "Update price",
	   description = "Please update the price of the product",
	   responses = {
			   @ApiResponse(responseCode = "200", description = "Updated"),
			   @ApiResponse(responseCode = "404", description = "Please enter a valid number")
	})
	public ResponseEntity<HttpStatus> updatePrice(@PathVariable int price, @PathVariable String itemName) {
		int result = storeService.updatePrice(price, itemName);
		if(result!=0) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
