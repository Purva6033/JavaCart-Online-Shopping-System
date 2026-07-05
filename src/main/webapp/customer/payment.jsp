<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%
Double total = (Double) session.getAttribute("totalAmount");

if (total == null) {
    total = 0.0;
}

String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Payment Gateway</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>

body{
    background:#f4f6f9;
}

.payment-card{
    max-width:700px;
    margin:50px auto;
    background:white;
    padding:35px;
    border-radius:15px;
    box-shadow:0 5px 20px rgba(0,0,0,.15);
}

.title{
    text-align:center;
    color:#0d6efd;
    font-weight:bold;
    margin-bottom:25px;
}

.total{
    text-align:right;
    font-size:30px;
    color:green;
    font-weight:bold;
}

</style>

</head>

<body>

<div class="container">

<div class="payment-card">

<h2 class="title">

<i class="bi bi-credit-card-fill"></i>

Payment Gateway

</h2>

<%
if(error != null){
%>

<div class="alert alert-danger">

<%=error%>

</div>

<%
}
%>

<form action="<%=request.getContextPath()%>/payment"
method="post">

<div class="mb-3">

<label class="form-label">

Card Holder Name

</label>

<input
type="text"
name="cardName"
class="form-control"
placeholder="Enter Card Holder Name"
required>

</div>

<div class="mb-3">

<label class="form-label">

Card Number

</label>

<input
type="text"
name="cardNumber"
class="form-control"
maxlength="16"
pattern="[0-9]{16}"
placeholder="1234123412341234"
required>

</div>

<div class="row">

<div class="col-md-6">

<label class="form-label">

Expiry Date

</label>

<input
type="month"
name="expiry"
class="form-control"
required>

</div>

<div class="col-md-6">

<label class="form-label">

CVV

</label>

<input
type="password"
name="cvv"
class="form-control"
maxlength="3"
pattern="[0-9]{3}"
placeholder="123"
required>

</div>

</div>

<div class="total mt-4">

Total : ₹ <%=String.format("%.2f", total)%>

</div>

<hr>

<div class="d-grid">

<button
type="submit"
class="btn btn-success btn-lg">

<i class="bi bi-lock-fill"></i>

Pay Now

</button>

</div>

<div class="text-center mt-3">

<a href="<%=request.getContextPath()%>/checkout"
class="btn btn-secondary">

<i class="bi bi-arrow-left-circle"></i>

Back To Checkout

</a>

</div>

</form>

</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>