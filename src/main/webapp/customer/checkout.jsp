<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Checkout</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>

body{

background:#f5f5f5;

}

.checkout-card{

max-width:650px;

margin:60px auto;

background:white;

padding:35px;

border-radius:15px;

box-shadow:0 5px 15px rgba(0,0,0,.15);

}

.total{

font-size:30px;

font-weight:bold;

color:green;

}

</style>

</head>

<body>

<div class="container">

<div class="checkout-card">

<h2 class="text-center mb-4">

<i class="bi bi-cart-check-fill"></i>

Checkout

</h2>

<hr>

<h4>

Grand Total

</h4>

<p class="total">

₹ ${grandTotal}

</p>

<hr>

<div class="d-grid">

<a href="<%=request.getContextPath()%>/customer/payment.jsp"
class="btn btn-success btn-lg">

<i class="bi bi-credit-card-fill"></i>

Proceed To Payment

</a>

</div>

<div class="text-center mt-3">

<a href="<%=request.getContextPath()%>/myCart"
class="btn btn-secondary">

<i class="bi bi-arrow-left-circle"></i>

Back To Cart

</a>

</div>

</div>

</div>

</body>

</html>