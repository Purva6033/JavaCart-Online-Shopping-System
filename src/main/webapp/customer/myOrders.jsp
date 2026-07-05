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
<title>My Orders</title>

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

tr:nth-child(even){
    background:#f9f9f9;
}

</style>

</head>

<body>

<h2>My Orders</h2>

<table>

<tr>

<th>Order ID</th>
<th>Total Amount</th>
<th>Status</th>
<th>Order Date</th>
<th>Action</th>

</tr>

<%

if(orderList != null){

    for(Order order : orderList){

%>

<tr>

<td><%=order.getOrderId()%></td>

<td>₹ <%=order.getTotalAmount()%></td>

<td><%=order.getOrderStatus()%></td>

<td><%=order.getOrderDate()%></td>

<td>

<a href="<%=request.getContextPath()%>/orderDetails?orderId=<%=order.getOrderId()%>">View Details </a>

</td>

</tr>

<%

    }

}

%>

</table>

</body>
</html>