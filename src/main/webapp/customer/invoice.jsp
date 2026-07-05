<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.User"%>
<%@ page import="com.javacart.model.Order"%>
<%@ page import="com.javacart.model.OrderDetail"%>

<%

User user=(User)request.getAttribute("user");

Order order=(Order)request.getAttribute("order");

List<OrderDetail> orderDetails=
(List<OrderDetail>)request.getAttribute("orderDetails");

if(user==null || order==null){

    response.sendRedirect(request.getContextPath()+"/customer/login.jsp");
    return;

}

%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Invoice</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

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

.invoice{

background:white;
padding:40px;
border-radius:15px;
box-shadow:0 5px 20px rgba(0,0,0,.15);
margin-top:40px;
margin-bottom:40px;

}

.company{

font-size:38px;
font-weight:bold;
color:#0d6efd;

}

.invoice-title{

font-size:28px;
font-weight:bold;

}

.info-box{

background:#f8f9fa;
padding:20px;
border-radius:10px;

}

.table th{

background:#0d6efd;
color:white;

}

.total{

font-size:28px;
font-weight:bold;
color:green;

}

@media print{

.no-print{

display:none;

}

body{

background:white;

}

.invoice{

box-shadow:none;
margin:0;

}

}

</style>

</head>

<body>

<div class="container">

<div class="invoice">

<div class="row">

<div class="col-md-6">

<div class="company">

🛒 JavaCart

</div>

<h4>

ORDER INVOICE

</h4>

</div>

<div class="col-md-6 text-end">

<h5>

Invoice No

</h5>

<h3>

INV-<%=order.getOrderId()%>

</h3>

</div>

</div>

<hr>

<div class="row mt-4">

<div class="col-md-6">

<div class="info-box">

<h5>

Customer Details

</h5>

<p>

<b>Name :</b>

<%=user.getFullName()%>

</p>

<p>

<b>Email :</b>

<%=user.getEmail()%>

</p>

<p>

<b>Phone :</b>

<%=user.getPhone()%>

</p>

<p>

<b>Address :</b>

<%=user.getAddress()%>

</p>

</div>

</div>

<div class="col-md-6">

<div class="info-box">

<h5>

Order Details

</h5>

<p>

<b>Order ID :</b>

#<%=order.getOrderId()%>

</p>

<p>

<b>Date :</b>

<%=order.getOrderDate()%>

</p>

<p>

<b>Status :</b>

<span class="badge bg-success">

<%=order.getOrderStatus()%>

</span>

</p>

<p>

<b>Total :</b>

₹ <%=String.format("%.2f",order.getTotalAmount())%>

</p>

</div>

</div>

</div>

<hr>

<h4 class="mt-4">

Products

</h4>

<table class="table table-bordered table-hover">

<thead>

<tr>

<th>Image</th>

<th>Product</th>

<th>Quantity</th>

<th>Price</th>

<th>Total</th>

</tr>

</thead>

<tbody>

<%

double grandTotal=0;

if(orderDetails!=null){

for(OrderDetail detail : orderDetails){

double total=
detail.getQuantity()*detail.getPrice();

grandTotal+=total;

%>

<tr>

<td width="120">

<img
src="<%=request.getContextPath()%>/images/<%=detail.getImageUrl()%>"
class="img-fluid"
style="height:80px;object-fit:contain;">

</td>

<td>

<%=detail.getProductName()%>

</td>

<td>

<%=detail.getQuantity()%>

</td>

<td>

₹ <%=String.format("%.2f",detail.getPrice())%>

</td>

<td>

₹ <%=String.format("%.2f",total)%>

</td>

</tr>

<%

}

}

%>

</tbody>

</table>
<hr>

<div class="row mt-4">

<div class="col-md-6">

<h5>

Thank You for Shopping with JavaCart!

</h5>

<p>

Your order has been successfully placed.

We appreciate your trust and hope to serve you again.

</p>

</div>

<div class="col-md-6 text-end">

<h4>

Grand Total

</h4>

<h2 class="total">

₹ <%=String.format("%.2f",grandTotal)%>

</h2>

</div>

</div>

<hr>

<div class="text-center mt-4 no-print">

<button
class="btn btn-primary btn-lg"
onclick="window.print()">

<i class="bi bi-printer-fill"></i>

Print Invoice

</button>

<a
href="<%=request.getContextPath()%>/myOrders"
class="btn btn-secondary btn-lg ms-2">

<i class="bi bi-arrow-left-circle"></i>

Back To Orders

</a>

</div>

</div>

</div>

<footer class="bg-dark text-white text-center p-4 no-print">

<div class="container">

<h5>

JavaCart

</h5>

<p>

Your One-Stop Online Shopping Destination

</p>

<hr>

<p class="mb-0">

© 2026 JavaCart | All Rights Reserved.

</p>

</div>

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>