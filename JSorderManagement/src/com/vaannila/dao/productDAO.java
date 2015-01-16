package com.vaannila.dao;

import java.util.List;






import com.vaannila.domain.inventory;
import com.vaannila.domain.logindetails;
import com.vaannila.domain.orderdata;
import com.vaannila.domain.productdetails;


public interface productDAO {

	public List<logindetails> getUserInfo(String user);
	
	public productdetails getProductInfo(String productId);
	
	public List<inventory> getInventoryData(String productId);
	
	public String getProductNameFromId(String productId);
	
	public void saveOrderDetails(orderdata orderdata);
	
	public void updateInventoryQuantity(String productId, String qty);
	
	public List<orderdata> getOrderDetails(String userId);
	
	public void cancelOrder(String orderId);

}
