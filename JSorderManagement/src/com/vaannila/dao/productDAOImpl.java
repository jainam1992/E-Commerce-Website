package com.vaannila.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.vaannila.domain.inventory;
import com.vaannila.domain.logindetails;
import com.vaannila.domain.orderdata;
import com.vaannila.domain.productdetails;


public class productDAOImpl implements productDAO{
	
	productdetails productdetails = new productdetails();
	
	@SessionTarget
	Session session;
	@TransactionTarget
	Transaction transaction;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<logindetails> getUserInfo(String user) {	
		List<logindetails> userList = null;
		try {
			userList = session.createQuery("from logindetails where username='"+user+"'").list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return userList;
	}
	
	
	@Override
	public productdetails getProductInfo(String productId)
	{
	try{   
	 // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         // Now connect to your databases
         DB db = mongoClient.getDB( "test" );
         DBCollection coll = db.getCollection("product_data");
         //BasicDBObject fields = new BasicDBObject();
     	 //fields.put("name", 1);
         BasicDBObject whereQuery = new BasicDBObject();
         whereQuery.put("id", productId);
         System.out.println("Connect to database successfully");
         System.out.println("Collection mycol selected successfully");
         DBCursor cursor = coll.find(whereQuery);
         int i=1;
         while (cursor.hasNext()) { 
        	 System.out.println("Inserted Document: "+i); 
        	 //System.out.println(cursor.next());
        	 DBObject dbObject= cursor.next();
        	 String startDate = (String) dbObject.get("startdate");
        	 String endDate = (String) dbObject.get("enddate");
        	 String category = (String) dbObject.get("category");
        	 String subcategory = (String) dbObject.get("subcategory");
        	 String name = (String) dbObject.get("name");
        	 String spec = (String) dbObject.get("spec");
        	 String path = (String) dbObject.get("path");
        	 String rating = (String) dbObject.get("rating");
        	 String price = (String) dbObject.get("price");
        	 //String reviews = (String) dbObject.get("reviews");
        	 String id = (String) dbObject.get("id");
        	 String size = (String) dbObject.get("size");
        	 
        	 productdetails = new productdetails();
        	 productdetails.setStartdate(startDate);
        	 productdetails.setEnddate(endDate);
        	 productdetails.setId(id);
        	 productdetails.setCategory(subcategory);
        	 productdetails.setName(name);
        	 productdetails.setPath(path);
        	 productdetails.setPrice(price);        	 
        	 productdetails.setSpec(spec);
        	 //productInfo.setReviews(reviews);
        	 productdetails.setCategory(category);
        	 productdetails.setSubcategory(subcategory);
        	 productdetails.setSize(size);
        	 System.out.println("start date-->"+startDate);
        	 System.out.println("size-->"+size);
        	 i++;
         }
      }catch(Exception e){
	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
	return productdetails;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<inventory> getInventoryData(String productId) {
		// TODO Auto-generated method stub
		List<inventory> inventoryList = null;
		try {
			inventoryList = session.createQuery("from inventory where id='"+Integer.parseInt(productId)+"'").list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return inventoryList;
	}
	
	
	public String getProductNameFromId(String productId) {	
		
		String productName = "";
		try {
			productName = this.getProductInfo(productId).getName();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return productName;
	}


	@Override
	public void saveOrderDetails(orderdata orderdata) {
		// TODO Auto-generated method stub
		try {
			session.save(orderdata);
			System.out.println("order placed and data saved in orderdata table .. ");
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}


	@Override
	public void updateInventoryQuantity(String productId, String qty) {
		// TODO Auto-generated method stub		
		String query = "UPDATE inventory SET quantity = quantity -"+ Integer.parseInt(qty) +" WHERE id ="+Integer.parseInt(productId);
		try {
		    session.getTransaction().begin();
		    session.createSQLQuery(query).executeUpdate();
		    session.getTransaction().commit();			    
		}
		catch (HibernateException erro){			    
		    System.out.println(erro.getMessage());
		    session.getTransaction().rollback();			    
		} 		
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<orderdata> getOrderDetails(String userId) {
		List<orderdata> orderList = null;
		try {
			orderList = session.createQuery("from orderdata where user_id="+Integer.parseInt(userId)).list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return orderList;
	}
	
	@Override
	public void cancelOrder(String orderId) {
					
		SQLQuery query = session.createSQLQuery("SELECT product_id,order_qty FROM orderdata WHERE order_id ="+orderId);
		String prodId = null, qty = null;
		List<Object[]> rows  = query.list();
		for(Object[] row : rows){
			prodId=row[0].toString();
			qty=row[1].toString();
		}
		SQLQuery query2 = session.createSQLQuery("UPDATE inventory SET quantity = quantity +"+Integer.parseInt(qty)+" WHERE id ="+Integer.parseInt(prodId));
		int updated=query2.executeUpdate();
		System.out.println("quantity added back to inventory .. ");
		
		
		SQLQuery query3 = session.createSQLQuery("UPDATE orderdata SET order_status='Cancelled' WHERE order_id ="+Integer.parseInt(orderId));
		int updated2=query3.executeUpdate();
		System.out.println("orderdata table updated .. ");
		
		session.getTransaction().commit();
		return;
	}
	
	
}