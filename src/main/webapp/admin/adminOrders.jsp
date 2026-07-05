<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.Order"%>

<%
List<Order> orderList =
(List<Order>)request.getAttribute("orderList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Orders</title>

<style>

body{
    background:
        linear-gradient(rgba(255,255,255,0.80),
                        rgba(255,255,255,0.80)),
        url("../images/background.jpg");

    background-size:500px;
    background-repeat:repeat;
    background-attachment:fixed;
}

h2{
    text-align:center;
}

table{
    width:100%;
    border-collapse:collapse;
    background:white;
}

th,td{
    border:1px solid #ddd;
    padding:12px;
    text-align:center;
}

th{
    background:#2c3e50;
    color:white;
}

select{
    padding:6px;
}

button{
    padding:6px 12px;
    background:green;
    color:white;
    border:none;
    cursor:pointer;
}

</style>

</head>

<body>

<h2>Manage Orders</h2>

<table>

<tr>

<th>Order ID</th>
<th>User ID</th>
<th>Total</th>
<th>Status</th>
<th>Date</th>
<th>Update Status</th>

</tr>

<%

if(orderList != null){

for(Order order : orderList){

%>

<tr>

<td><%=order.getOrderId()%></td>

<td><%=order.getUserId()%></td>

<td>₹ <%=order.getTotalAmount()%></td>

<td><%=order.getOrderStatus()%></td>

<td><%=order.getOrderDate()%></td>

<td>

<form action="<%=request.getContextPath()%>/updateOrderStatus"
method="post">

<input type="hidden"
name="orderId"
value="<%=order.getOrderId()%>">

<select name="status">

<option>Pending</option>
<option>Packed</option>
<option>Shipped</option>
<option>Delivered</option>
<option>Cancelled</option>

</select>

<button type="submit">

Update

</button>

</form>

</td>

</tr>

<%

}

}

%>

</table>

</body>
</html>