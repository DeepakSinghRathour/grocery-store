package com.grocery.store.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private StoreService storeService;
	
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
		String result = storeService.updateQuantity(quantity, itemName);
		logger.info(result);

		if(result.equals("Quantity updated successfully.")) {
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
		String result = storeService.updatePrice(price, itemName);
		
		logger.info(result);
		
		if(result.equals("Price is updated successfully..........")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
