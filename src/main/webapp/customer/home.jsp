<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.Product"%>
<%@ page import="com.javacart.model.Category"%>
<%@ page import="com.javacart.model.User"%>

<%
User user = (User) session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}

List<Product> productList =
(List<Product>)request.getAttribute("productList");

List<Category> categoryList =
(List<Category>)request.getAttribute("categoryList");

String selectedCategory =
(String)request.getAttribute("selectedCategory");

if(selectedCategory == null){
    selectedCategory="All";
}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>JavaCart</title>

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

.navbar-brand{
    font-size:28px;
    font-weight:bold;
}

.sidebar{

    background:white;
    border-radius:12px;
    box-shadow:0 2px 15px rgba(0,0,0,.12);
    padding:20px;

    position:sticky;
    top:90px;
    height:fit-content;

}

.sidebar h4{

    margin-bottom:20px;
    font-weight:bold;

}

.sidebar a{

    display:block;
    text-decoration:none;
    color:#333;
    padding:12px;
    margin-bottom:8px;
    border-radius:8px;
    transition:.3s;

}

.sidebar a:hover{

    background:#0d6efd;
    color:white;
    transform:translateX(5px);

}

.active-category{

    background:#0d6efd;
    color:white !important;
    font-weight:bold;

}

.product-card{

    border:none;
    border-radius:15px;
    overflow:hidden;
    transition:.4s;
    box-shadow:0 3px 15px rgba(0,0,0,.15);

}

.product-card:hover{

    transform:translateY(-10px);
    box-shadow:0 12px 30px rgba(0,0,0,.2);

}

.product-card img{

    height:220px;
    object-fit:contain;
    padding:20px;

}

.price{

    font-size:24px;
    color:green;
    font-weight:bold;

}

footer{

    background:#212529;
    color:white;
    margin-top:60px;
    padding:30px;

}

.carousel-item{

    height:320px;

}

.banner{

    height:320px;
    display:flex;
    flex-direction:column;
    justify-content:center;
    padding-left:60px;

}

.banner h1{

    font-size:48px;
    font-weight:bold;

}

.banner p{

    font-size:22px;

}

</style>

</head>

<body>

<!-- NAVBAR -->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">

<div class="container">

<a class="navbar-brand"
href="<%=request.getContextPath()%>/customerHome">

🛒 JavaCart

</a>

<button class="navbar-toggler"
type="button"
data-bs-toggle="collapse"
data-bs-target="#navbarNav">

<span class="navbar-toggler-icon"></span>

</button>

<div class="collapse navbar-collapse" id="navbarNav">

<form class="d-flex mx-auto" action="<%=request.getContextPath()%>/customerHome" method="get">

<input class="form-control me-2"  type="search" name="keyword" value="<%=request.getAttribute("keyword")==null?"":request.getAttribute("keyword")%>"
       placeholder="Search Products...">
<button class="btn btn-warning">

<i class="bi bi-search"></i>

</button>

</form>

<ul class="navbar-nav ms-auto">

<li class="nav-item">

<a class="nav-link active" href="<%=request.getContextPath()%>/customerHome">

Home

</a>

</li>

<li class="nav-item">

<a class="nav-link position-relative"
href="<%=request.getContextPath()%>/myCart">

<i class="bi bi-cart-fill"></i>

My Cart

<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">

${sessionScope.cartCount}

</span>

</a>

</li>

<li class="nav-item">

<a class="nav-link" href="<%=request.getContextPath()%>/myOrders">

Orders

</a>

</li>

<li class="nav-item">

<a class="nav-link text-warning"> 
Welcome,
<%=user.getFullName()%>

</a>

</li>
<li class="nav-item">
    <a class="nav-link"
       href="<%=request.getContextPath()%>/profile">

        <i class="bi bi-person-circle"></i>

        My Profile

    </a>
</li>

<li class="nav-item">

<a class="nav-link text-danger" href="<%=request.getContextPath()%>/logout"> Logout </a>

</li>

</ul>

</div>

</div>

</nav>




<div class="container mt-4">

    <div class="p-5 rounded-4 shadow text-white" style="background:linear-gradient(135deg,#0d6efd,#0a58ca);">

        <div class="row align-items-center">

            <div class="col-md-8">

                <h1 class="display-4 fw-bold">

                    🛒 Welcome to JavaCart

                </h1>

                <p class="lead mt-3">

                    Discover the latest Electronics, Mobiles, Fashion, Books, Gaming, Home & Kitchen and much more.

                </p>

                <h3 class="mt-4 text-warning">

                    🔥 Mega Shopping Sale

                </h3>

                <p>

                    Up to <b>10% OFF</b> on selected products with Free Delivery.

                </p>

                <a href="<%=request.getContextPath()%>/customerHome"
                   class="btn btn-warning btn-lg mt-2">

                    <i class="bi bi-bag-fill"></i>

                    Shop Now

                </a>

            </div>

            <div class="col-md-4 text-center">

                <i class="bi bi-cart4"  style="font-size:180px;color:white;opacity:.9;"></i>

            </div>

        </div>

    </div>

</div>

<button class="carousel-control-prev" type="button" data-bs-target="#offerCarousel" data-bs-slide="prev">

<span class="carousel-control-prev-icon"></span>

</button>

<button class="carousel-control-next" type="button" data-bs-target="#offerCarousel" data-bs-slide="next">

<span class="carousel-control-next-icon"></span>

</button>

</div>

</div>

<!-- MAIN CONTENT -->

<div class="container mt-4">

<div class="row">

<!-- CATEGORY SIDEBAR -->

<div class="col-lg-3">

<div class="sidebar">

<h4>📂 Categories</h4>

<a class="<%=selectedCategory.equals("All")?"active-category":""%>" href="<%=request.getContextPath()%>/customerHome"> 🏠 All Products </a>

<%
if(categoryList!=null){

for(Category category:categoryList){
%>

<a class="<%=selectedCategory.equals(category.getCategoryName())?"active-category":""%>" 

href="<%=request.getContextPath()%>/customerHome?category=<%=URLEncoder.encode(category.getCategoryName(),"UTF-8")%>">

<%=category.getCategoryName()%>

</a>

<%
}
}
%>

</div>

</div>



<div class="col-lg-9">

<div class="row">
<%
if(productList != null && !productList.isEmpty()){

    for(Product product : productList){
%>

<div class="col-lg-4 col-md-6 mb-4">

    <div class="card product-card h-100 position-relative">

        
        <span class="badge bg-danger position-absolute m-2">  NEW </span>

       
        <img src="<%=request.getContextPath()%>/images/<%=product.getImageUrl()%>" class="card-img-top" alt="<%=product.getProductName()%>">

        <div class="card-body text-center">

           
            <h5 class="card-title fw-bold">
                <%=product.getProductName()%>
            </h5>

          
            <div class="mb-2">

                <span class="badge bg-success">  ⭐ 4.8  </span>

                <small class="text-muted ms-1">  (1,275 Ratings) </small>

            </div>

            
            <p class="text-secondary mb-2">

                <i class="bi bi-tag-fill"></i>

                <%=product.getCategory()%>

            </p>

           
            <p class="card-text text-muted" style="height:60px;overflow:hidden;">

                <%=product.getDescription()%>

            </p>

           
            <div class="mb-2">

                <span class="price">

                    ₹ <%=String.format("%.2f",product.getPrice())%>

                </span>

                <br>

                <del class="text-muted">

                    ₹ <%=String.format("%.2f",product.getPrice()+5000)%>

                </del>

                <span class="badge bg-danger ms-2">  10% OFF </span>

            </div>


            <p class="text-success fw-bold mb-3">

                🚚 Free Delivery

            </p>

            <%
            if(product.getStockQuantity()>0){
            %>

            <span class="badge bg-success mb-3">  In Stock </span>

            <%
            }else{
            %>

            <span class="badge bg-danger mb-3"> Out Of Stock </span>

            <%
            }
            %>

            <div class="d-grid gap-2">

                <a href="<%=request.getContextPath()%>/productDetails?productId=<%=product.getProductId()%>"
                   class="btn btn-outline-primary">

                    <i class="bi bi-eye"></i>

                    View Details

                </a>

                <a href="<%=request.getContextPath()%>/addToCart?productId=<%=product.getProductId()%>"
                   class="btn btn-success">

                    <i class="bi bi-cart-plus"></i>

                    Add To Cart

                </a>

            </div>

        </div>

    </div>

</div>

<%
    }

}else{
%>

<div class="col-12">

    <div class="alert alert-warning text-center p-5">

        <h3>

            😔 No Products Available

        </h3>

        <p>

            Please try another category.

        </p>

    </div>

</div>

<%
}
%>

</div>

</div>

</div>

</div>

<%
Integer currentPage = (Integer)request.getAttribute("currentPage");
Integer totalPages = (Integer)request.getAttribute("totalPages");

if(currentPage != null && totalPages != null && totalPages > 1){
%>

<nav class="mt-4">

<ul class="pagination justify-content-center">

<% if(currentPage > 1){ %>

<li class="page-item">

<a class="page-link"
href="<%=request.getContextPath()%>/customerHome?page=<%=currentPage-1%>">

Previous

</a>

</li>

<% } %>

<%

for(int i=1;i<=totalPages;i++){

%>

<li class="page-item <%=i==currentPage?"active":""%>">

<a class="page-link"
href="<%=request.getContextPath()%>/customerHome?page=<%=i%>">

<%=i%>

</a>

</li>

<%

}

%>

<% if(currentPage < totalPages){ %>

<li class="page-item">

<a class="page-link"
href="<%=request.getContextPath()%>/customerHome?page=<%=currentPage+1%>">

Next

</a>

</li>

<% } %>

</ul>

</nav>

<%

}
%>

<div class="container mt-5">

<div class="row text-center">

<div class="col-md-4">

<i class="bi bi-truck fs-1 text-primary"></i>

<h5 class="mt-3">

Free Delivery

</h5>

<p>

On Orders Above ₹499

</p>

</div>

<div class="col-md-4">

<i class="bi bi-shield-check fs-1 text-success"></i>

<h5 class="mt-3">

Secure Payments

</h5>

<p>

100% Safe Transactions

</p>

</div>

<div class="col-md-4">

<i class="bi bi-arrow-repeat fs-1 text-danger"></i>

<h5 class="mt-3">

Easy Returns

</h5>

<p>

7 Days Return Policy

</p>

</div>

</div>

</div>

<!-- FOOTER -->

<footer>

<div class="container">

<div class="row">

<div class="col-md-4">

<h4>

🛒 JavaCart

</h4>

<p>

Your one-stop destination for online shopping.

</p>

</div>

<div class="col-md-4">

<h5>

Quick Links

</h5>

<p>

<a href="<%=request.getContextPath()%>/customerHome"
class="text-white text-decoration-none">

Home

</a>

</p>

<p>

<a href="<%=request.getContextPath()%>/myCart"
class="text-white text-decoration-none">

My Cart

</a>

</p>

<p>

<a href="<%=request.getContextPath()%>/myOrders"
class="text-white text-decoration-none">

Orders

</a>

</p>

</div>

<div class="col-md-4">

<h5>

Contact Us

</h5>

<p>

📧 support@javacart.com

</p>

<p>📞 +91 8625866033</p>
<p>📍 Pune, Maharashtra </p>

</div>

</div>

<hr>

<div class="text-center"> © 2026 JavaCart | All Rights Reserved </div>

</div>

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>