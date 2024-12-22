package com.grocery.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grocery.store.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>{
	
}
