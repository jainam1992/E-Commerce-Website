<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body background="css/images/mintyellow.jpg">
 
 
<form id="productConfirm" action="confirmProductOrder">
<a href="#"> <img src="${proddetails.path}" float="right" width="700" height="400" alt=""/></a>
<table width="52%" height="50%" align="left" border="2">

<tr>
	<td>
		<b>CATEGORY</b>
	</td>

    <td >
		${proddetails.category}
	</td>
	
	</tr>

<tr>

<td>
	<b>SUB-CATEGORY</b>
</td>
	<td>
		${proddetails.subcategory}
	</td>
</tr>

<tr>
	<td>
	<b>PRICE</b>
	</td>
	
	<td>
		${proddetails.price}
		<input type="hidden" id="price" name="price" value = "${proddetails.price}"/>
	</td>
</tr>

<tr>
	<td> 
	<b>Specifications</b>
	</td>
	
	<td>
		${proddetails.spec}
	</td>
</tr>

<tr>
	
	<td>
	<b>NAME</b>
	</td>
	
	<td>
		${proddetails.name}
	</td>
</tr>

<tr>
	
	<td>
	<b>SIZE</b>
	</td>
	<td>
		${proddetails.size}
	</td>
</tr>

</table>

 <br> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Select Quantity: <input type="text" name="qty" id="qty"> &nbsp; <input type="submit" value="Confirm Order" onclick="return checkQuantity()"/><br><br>

<input type="hidden" id="totalAmt" name="totalAmt"/>
<input type="hidden" id="availableQuantity" name="availableQuantity" value=${quantity} />
<input type="hidden" id="isProductValid" name="isProductValid" value=${isProductValid} />
</form>       
</body>
<script>
function calculateAmount(){	
	document.getElementById('totalAmt').value = document.getElementById('qty').value * document.getElementById('price').value;
}

function checkQuantity(){
	
	if(document.getElementById('isProductValid').value.trim() == "Y"){
	var requestedQuantity = document.getElementById('qty').value.trim();
	var availableQuantity = document.getElementById('availableQuantity').value.trim();
	
	if(requestedQuantity != 0){		
			if(requestedQuantity>availableQuantity){
			alert("You have exceeded Available quantity ("+availableQuantity+")");
			return false;
		}else{
			calculateAmount();
		}
	}else{
		alert("Please enter quantity");
		return false;
	}
	
	}else{
		alert("Product is no more valid");
		return false;
	}
}
</script>