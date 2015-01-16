<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="imports.jsp" %>

<body background="css/images/mintyellow.jpg">
<font size="5"><CENTER><u><b><i> Order History </i></b></u></CENTER></font>
<br>
<table width="99%" height="99%" align="center" border="2">

<tr>
	<td class="ui-state-default">
		<b>Product Name</b>
	</td>
	
	<td class="ui-state-default">
		<b>User Id</b>
	</td>
	
	<td class="ui-state-default">
		<b>Product Id</b>
	</td>
	
	<td class="ui-state-default">
		<b>Order Date</b>
	</td>
	
	<td class="ui-state-default">
		<b>Order Status</b>
	</td>
	
	<td class="ui-state-default">
		<b>Order Quantity</b>
	</td>
	
	<td class="ui-state-default">
		<b>Rate</b>
	</td>
	
	<td class="ui-state-default">
		<b>Order Id</b>
	</td>

</tr>

		<s:iterator value="orderList" var="orderItem" >
			<tr align="center">
				<td class="ui-state-active">${productName}</td>
				<td class="ui-state-active">${user_id}</td>
				<td class="ui-state-active">${product_id}</td>
				<td class="ui-state-active">${order_date}</td>
				<s:if test="#orderItem.order_status=='Pending'">
					<td width="20%" id="${order_id}_order" class="ui-state-default">${order_status}<br><a href="" onclick="cancelThisOrder(${order_id});" ><u>Cancel order</u></a></td>
				</s:if>
				<s:else>
					<s:if test="#orderItem.order_status=='Delivered'">
					<td width="20%" id="${order_id}_order" class="ui-state-active">${order_status}<br></td>
					</s:if>						
					<s:if test="#orderItem.order_status=='Cancelled'">
					<td width="20%" id="${order_id}_order" class="ui-state-error">${order_status}<br></td>
					</s:if>																						
				</s:else>
				<td class="ui-state-active">${order_qty}</td>
				<td class="ui-state-active">$${rate}</td>
				<td class="ui-state-active">${order_id}</td>				
			</tr>
		</s:iterator>
   
</table>
</body>
<script type="text/javascript">

function cancelThisOrder(order_id)
{
	$.ajax({
		type: "POST",
        url: "orderCancellation?orderId="+order_id,
        dataType: "text",
        cache: false,
        timeout: 5000,
        success: function(data) {        	
        	$("#"+order_id+"_order").html("Cancelled");
        	$("#"+order_id+"_order").addClass( "ui-state-error" );        	
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	alert("error"+textStatus+"-----"+ errorThrown);
        }
    });
}
</script>
