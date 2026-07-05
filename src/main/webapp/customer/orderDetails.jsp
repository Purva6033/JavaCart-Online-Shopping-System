<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.OrderDetail"%>

<%
List<OrderDetail> detailList =
(List<OrderDetail>)request.getAttribute("detailList");

Integer orderId =
(Integer)request.getAttribute("orderId");

double grandTotal = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details</title>

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
    color:#2c3e50;
}

table{
    width:100%;
    border-collapse:collapse;
    background:white;
    box-shadow:0 0 10px lightgray;
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

img{
    width:100px;
    height:100px;
}

.back{
    display:inline-block;
    margin-top:20px;
    padding:10px 20px;
    background:#007bff;
    color:white;
    text-decoration:none;
    border-radius:5px;
}

.total{
    margin-top:20px;
    text-align:right;
    font-size:22px;
    font-weight:bold;
}

</style>

</head>

<body>

<h2>Order Details</h2>

<table>

<tr>

<th>Image</th>
<th>Product</th>
<th>Price</th>
<th>Quantity</th>
<th>Total</th>

</tr>

<%

if(detailList != null){

    for(OrderDetail item : detailList){

        double total = item.getPrice() * item.getQuantity();

        grandTotal += total;

%>

<tr>

<td>

<img src="<%=request.getContextPath()%>/images/<%=item.getImageUrl()%>">

</td>

<td>

<%=item.getProductName()%>

</td>

<td>

₹ <%=item.getPrice()%>

</td>

<td>

<%=item.getQuantity()%>

</td>

<td>

₹ <%=total%>

</td>

</tr>

<%

    }

}

%>

</table>

<div class="total">

Grand Total : ₹ <%=grandTotal%>

</div>

<br>
<a href="<%=request.getContextPath()%>/invoice?orderId=<%=orderId%>"
class="back"
style="background:#28a745; margin-right:10px;">

Invoice

</a>

<a class="back"
href="<%=request.getContextPath()%>/myOrders">

Back To My Orders

</a>

</body>
</html>