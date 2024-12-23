package com.grocery.store.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(QueryService.class);

	@Autowired
	public QueryService(StoreRepository storeRepository, EntityManager entityManager) {
		this.storeRepository = storeRepository;
		this.entityManager = entityManager;
	}

	@Transactional
	public String updateQuantity(int quantity, String itemName) {

		try {
			String queryStr = QueryLoaderService.getInstance().getQuery("updateQuantity");

			if (quantity > 0) {
				Query query = entityManager.createQuery(queryStr);
				query.setParameter("itemName", itemName);
				query.setParameter("quantity", quantity);
				int rowsUpdated = query.executeUpdate();
				logger.info("Rows updated: " + rowsUpdated);

				if (rowsUpdated > 0) {
					return "Quantity updated successfully.";
				} else {
					return "No rows were updated. Item may not exist.";
				}
			} else {
				return "Quantity is very less";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "Something went wrong while updating quantity";
		}
	}

	@Transactional
	public String updatePrice(double price, String itemName) {

		try {

			String queryStr = QueryLoaderService.getInstance().getQuery("updatePrice");

			if (price > 0) {
				Query query = entityManager.createQuery(queryStr);
				query.setParameter("price", price);
				query.setParameter("itemName", itemName);
				query.executeUpdate();
				return "Price is updated successfully..........";
			} else {
				return "Price is very less";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "Something went wrong while updating the price..";
		}
	}

	@Transactional
	public String totalPayableAmount(String itemName, int quantity) {

		if (quantity > 0) {

			try {
				/* Check if itemName is available */
				String queryStr1 = QueryLoaderService.getInstance().getQuery("checkItemNameIsPresent");
				Query query1 = entityManager.createQuery(queryStr1);
				query1.setParameter("itemName", itemName);
				Long getItemName = (Long) query1.getSingleResult();
				if (getItemName == 0) {
					return "Sorry, " + itemName + " is not present.";
				}
				logger.info("item Name is " + itemName);

				/* Check if input quantity is available or not */
				String queryStr2 = QueryLoaderService.getInstance().getQuery("getQuantity");
				Query query2 = entityManager.createQuery(queryStr2);
				query2.setParameter("itemName", itemName);
				Integer getQuantity = (Integer) query2.getSingleResult();
				if (getQuantity < quantity) {
					return "Sorry for inconventience currently you can select upto " + getQuantity + " of " + itemName;
				}
				logger.info("Present quantity is : " + getQuantity);

				/* Get the price */
				String queryStr3 = QueryLoaderService.getInstance().getQuery("getPrice");
				Query query3 = entityManager.createQuery(queryStr3);
				query3.setParameter("itemName", itemName);

				Double price = (Double) query3.getSingleResult();
				Integer priceAsInteger = price.intValue();
				logger.info("price is : " + priceAsInteger);

				/* Remove the quantity from table */
				String queryStr4 = QueryLoaderService.getInstance().getQuery("updateQuantityAfterShopping");
				Query query4 = entityManager.createQuery(queryStr4);
				int updatedQuantity = getQuantity - quantity;
				logger.info("right now quantity is : " + quantity);
				query4.setParameter("itemName", itemName);
				query4.setParameter("quantity", updatedQuantity);

				int rowsUpdated = query4.executeUpdate();
				if (rowsUpdated > 0) {
					logger.info("Quantity successfully updated for item: " + itemName);
				} else {
					logger.warn("Failed to update quantity for item: " + itemName);
				}

				return "Thanks for shopping, please pay " + (priceAsInteger * quantity);

			} catch (Exception e) {
				logger.info(e.getMessage());
				return "Something went wrong....";
			}
		} else {
			return "Please enter the quantity more than 0.";
		}
	}
}
