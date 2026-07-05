<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.javacart.model.Product"%>

<%
Product product = (Product)request.getAttribute("product");

if(product == null){

    response.sendRedirect(request.getContextPath()+"/customerHome");
    return;

}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title><%=product.getProductName()%></title>

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

.product-image{

height:420px;
object-fit:contain;
background:white;
padding:30px;
border-radius:15px;
box-shadow:0 2px 10px rgba(0,0,0,.1);

}

.details{

background:white;
padding:30px;
border-radius:15px;
box-shadow:0 2px 10px rgba(0,0,0,.1);

}

.price{

font-size:36px;
font-weight:bold;
color:green;

}

.category{

display:inline-block;
padding:6px 12px;
background:#0d6efd;
color:white;
border-radius:20px;
margin-top:10px;

}

.rating{

font-size:20px;
color:orange;

}

.stock{

font-weight:bold;
font-size:18px;

}

.desc{

margin-top:20px;
line-height:1.8;

}

</style>

</head>

<body>

<nav class="navbar navbar-dark bg-dark">

<div class="container">

<a class="navbar-brand"
href="<%=request.getContextPath()%>/customerHome">

🛒 JavaCart

</a>

<a href="<%=request.getContextPath()%>/myCart"
class="btn btn-warning">

<i class="bi bi-cart-fill"></i>

My Cart

</a>

</div>

</nav>

<div class="container mt-5">

<div class="row">

<div class="col-md-5">

<img
src="<%=request.getContextPath()%>/images/<%=product.getImageUrl()%>"
class="img-fluid product-image">

</div>

<div class="col-md-7">

<div class="details">

<h2>

<%=product.getProductName()%>

</h2>

<div class="rating">

★★★★★
<small class="text-dark">

(4.8)

</small>

</div>

<div class="price mt-3">

₹ <%=product.getPrice()%>

</div>

<div class="category">

<%=product.getCategory()%>

</div>

<p class="stock mt-4">

<%

if(product.getStockQuantity()>0){

%>

<span class="text-success">

✅ In Stock

</span>

<%

}else{

%>

<span class="text-danger">

❌ Out of Stock

</span>

<%

}

%>

</p>

<div class="desc">

<h5>Description</h5>

<p>

<%=product.getDescription()%>

</p>

</div>

<div class="mt-4">

<a href="<%=request.getContextPath()%>/addToCart?productId=<%=product.getProductId()%>"
class="btn btn-success btn-lg">

<i class="bi bi-cart-plus"></i>

Add To Cart

</a>

<a href="<%=request.getContextPath()%>/customerHome"
class="btn btn-secondary btn-lg">

Back

</a>

</div>

</div>

</div>

</div>

</div>

<footer class="bg-dark text-white text-center mt-5 p-4">

© 2026 JavaCart | All Rights Reserved

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>