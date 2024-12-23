package com.grocery.store.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.store.entities.Store;
import com.grocery.store.services.StoreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name="Store", description="APIs related to store operations")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private StoreService storeService;
	
	@PostMapping("/payable-amount/{itemName}/{quantity}")
	@Operation(summary = "The Payable Amount",
	   description = "The amount which is should be paid by user",
	   responses = {
			   @ApiResponse(responseCode = "200", description = "Item found")
	})
	public ResponseEntity<Store> addItem(@PathVariable String itemName, @PathVariable int quantity) {
		
		logger.info("Inside controller class, User Controller - add item method - start");
		
		logger.info(storeService.totalPayableAmount(itemName, quantity));
		
		logger.info("Inside controller class, User Controller - add item method - end");		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
