package com.vaannila.web;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vaannila.dao.productDAO;
import com.vaannila.dao.productDAOImpl;
import com.vaannila.domain.inventory;
import com.vaannila.domain.logindetails;
import com.vaannila.domain.orderdata;
import com.vaannila.domain.productdetails;


public class UserAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String prodId,password,username,quantity,errorMsg,totalAmt,qty,orderId,isProductValid;

	public String getIsProductValid() {
		return isProductValid;
	}

	public void setIsProductValid(String isProductValid) {
		this.isProductValid = isProductValid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public productDAO productDAO = new productDAOImpl();
	logindetails logindetails = new logindetails();
	productdetails proddetails = new productdetails();	
	orderdata orderdata = new orderdata();
	List<orderdata> orderList = new ArrayList<orderdata>();
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public List<orderdata> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<orderdata> orderList) {
		this.orderList = orderList;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public productdetails getProddetails() {
		return proddetails;
	}

	public void setProddetails(productdetails proddetails) {
		this.proddetails = proddetails;
	}
	
	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	@SuppressWarnings("unchecked")
	public String list(){
		Map session = ActionContext.getContext().getSession();
		session.put("product_id",getProdId());
		return SUCCESS;		
	}
	

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String authenticate(){
		
		String username = getUsername();
		String password = getPassword();
		
		@SuppressWarnings("rawtypes")
		Map session = ActionContext.getContext().getSession();
		String product_id=(String) session.get("product_id");
		
		String encPass =encryptPassword(password);
		List<logindetails> userList= productDAO.getUserInfo(username);
		
		if(userList.size()!=0){
			if(encPass.equals(userList.get(0).getPassword())){
				session.put("user_id", userList.get(0).getId().toString());
				proddetails = productDAO.getProductInfo(product_id);
				setProddetails(proddetails);
				List<inventory> inventoryList = productDAO.getInventoryData(product_id);
				setQuantity(inventoryList.get(0).getQuantity());
				session.put("proddetails", proddetails);				
								
				orderdata = new orderdata();
				SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");		
				orderdata.setOrder_date(mdyFormat.format(new Date()));
				orderdata.setUser_id((String)session.get("user_id"));				
				orderdata.setOrder_status("Pending");
				orderdata.setProduct_id((String)session.get("product_id"));
				orderdata.setProductName(productDAO.getProductNameFromId((String)session.get("product_id")));
				System.out.println("product valid set");
				
				
				try {
					Date date = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(proddetails.getEnddate());
					if(date.before(new Date())){
						setIsProductValid("N");
						
					}else{
						setIsProductValid("Y");
						
					}
					
					System.out.println(getIsProductValid());
					System.out.println(date);
					System.out.println(proddetails.getEnddate());
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
											
				session.put("orderdata", orderdata);
				setErrorMsg("");
				return SUCCESS;
			}else{				
				setErrorMsg("Invalid Credentials");
				return "failure";				
			}			
		}
		
		setErrorMsg("Invalid User");
		return "failure";
	}
	
	public String placeOrder(){
		Map session = ActionContext.getContext().getSession();
		session.put("quantity", getQuantity());	
		session.put("requestedQty", getQty());
		orderdata orderdata = new orderdata();
		orderdata = (orderdata)session.get("orderdata");
		orderdata.setRate(getTotalAmt());
		orderdata.setOrder_qty(getQty());
		session.put("orderdata", orderdata);
		return SUCCESS;
	}
	
	public String getSuccessPage(){		
		Map session = ActionContext.getContext().getSession();		
		orderdata orderdata = new orderdata();
		orderdata = (orderdata)session.get("orderdata");		
		productDAO.saveOrderDetails((orderdata)session.get("orderdata"));
		productDAO.updateInventoryQuantity((String)session.get("product_id"), (String)session.get("requestedQty"));
		return SUCCESS;
	}
	
	public String getOrderDetails(){
		Map session = ActionContext.getContext().getSession();
		String userId = (String)session.get("user_id");	
		List<orderdata> orderDataList = new ArrayList<orderdata>(); 
		orderDataList = productDAO.getOrderDetails(userId);
		if(orderDataList.size() != 0){
			for(int i=0;i<orderDataList.size(); i++){
				orderdata orderdata = new orderdata();
				orderdata = (orderdata)orderDataList.get(i);
				orderdata.setProductName( productDAO.getProductNameFromId(orderdata.getProduct_id()));
				orderList.add(orderdata);
			}
			setOrderList(orderList);
		}		
		return SUCCESS;
	}
	
	public String cancelOrderDetails(){
		
		productDAO.cancelOrder(getOrderId());
		inputStream = new StringBufferInputStream("Y");		
		return SUCCESS;
	}
	
	
	private static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	        System.out.println("encrypted password : "+sha1);
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}


}
