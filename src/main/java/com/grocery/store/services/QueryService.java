package com.grocery.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.grocery.store.repository.StoreRepository;
import jakarta.persistence.EntityManager;

@Service
public class QueryService {

    public final StoreRepository storeRepository;
    public final EntityManager entityManager;

    @Autowired
    public QueryService(StoreRepository storeRepository, EntityManager entityManager) {
        this.storeRepository = storeRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public int updateQuantity(int quantity, String itemName) {
        String queryStr = QueryLoaderService.getInstance().getQuery("updateQuantity");

        if (quantity > 0) {
            Query query = entityManager.createQuery(queryStr);
            query.setParameter("quantity", quantity);
            query.setParameter("itemName", itemName);
            return query.executeUpdate();
        } else {
            return 0;
        }
    }

    @Transactional
    public int updatePrice(double price, String itemName) {
        String queryStr = QueryLoaderService.getInstance().getQuery("updatePrice");

        if (price > 0) {
            Query query = entityManager.createQuery(queryStr);
            query.setParameter("price", price);
            query.setParameter("itemName", itemName);
            return query.executeUpdate();
        } else {
            return 0;
        }
    }
}
