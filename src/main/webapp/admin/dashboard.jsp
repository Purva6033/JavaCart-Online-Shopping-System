<%@ page import="com.javacart.model.Admin"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.RecentOrder"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.TopSellingProduct"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Admin admin = (Admin)request.getAttribute("admin");

if(admin == null){
    admin = (Admin)session.getAttribute("admin");
}

if(admin == null){
    response.sendRedirect("login.jsp");
    return;
}

int totalProducts = request.getAttribute("totalProducts") != null ?
(Integer)request.getAttribute("totalProducts") : 0;

int totalCustomers = request.getAttribute("totalCustomers") != null ?
(Integer)request.getAttribute("totalCustomers") : 0;

int totalOrders = request.getAttribute("totalOrders") != null ?
(Integer)request.getAttribute("totalOrders") : 0;

double totalRevenue = request.getAttribute("totalRevenue") != null ?
(Double)request.getAttribute("totalRevenue") : 0;

List<RecentOrder> recentOrders =
(List<RecentOrder>)request.getAttribute("recentOrders");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>JavaCart Admin Dashboard</title>

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

.header{

background:#212529;
color:white;
padding:30px;
text-align:center;
margin-bottom:30px;

}

.header h1{

font-weight:bold;

}

.stat-card{

border:none;
border-radius:15px;
transition:.3s;
color:white;

}

.stat-card:hover{

transform:translateY(-5px);

}

.manage-card{

border:none;
border-radius:15px;
box-shadow:0 5px 15px rgba(0,0,0,.12);
transition:.3s;

}

.manage-card:hover{

transform:translateY(-5px);

}

footer{

background:#212529;
color:white;
padding:20px;
margin-top:40px;
text-align:center;

}

</style>

</head>

<body>

<div class="header">

<h1>

<i class="bi bi-speedometer2"></i>

JavaCart Admin Dashboard

</h1>

<h5>

Welcome,

<%=admin.getEmail()%>

</h5>

</div>

<div class="container">

<!-- Statistics -->

<div class="row">

<div class="col-md-3 mb-4">

<div class="card stat-card bg-primary text-center p-4">

<h1>

<i class="bi bi-box-seam"></i>

</h1>

<h5>Total Products</h5>

<h2>

<%=totalProducts%>

</h2>

</div>

</div>

<div class="col-md-3 mb-4">

<div class="card stat-card bg-success text-center p-4">

<h1>

<i class="bi bi-people-fill"></i>

</h1>

<h5>Customers</h5>

<h2>

<%=totalCustomers%>

</h2>

</div>

</div>

<div class="col-md-3 mb-4">

<div class="card stat-card bg-warning text-dark text-center p-4">

<h1>

<i class="bi bi-cart-fill"></i>

</h1>

<h5>Total Orders</h5>

<h2>

<%=totalOrders%>

</h2>

</div>

</div>

<div class="col-md-3 mb-4">

<div class="card stat-card bg-danger text-center p-4">

<h1>

<i class="bi bi-currency-rupee"></i>

</h1>

<h5>Revenue</h5>

<h2>

&#8377; <%=String.format("%.2f", totalRevenue)%>

</h2>
<%
List<TopSellingProduct> topProducts =
(List<TopSellingProduct>)request.getAttribute("topProducts");
%>

<div class="card shadow mt-4">

    <div class="card-header bg-success text-white">

        <h4 class="mb-0">

            🏆 Top Selling Products

        </h4>

    </div>

    <div class="card-body">

        <table class="table table-hover">

            <thead>

            <tr>

                <th>Rank</th>

                <th>Product</th>

                <th>Units Sold</th>

            </tr>

            </thead>

            <tbody>

            <%

            if(topProducts!=null && !topProducts.isEmpty()){

                int rank=1;

                for(TopSellingProduct p : topProducts){

            %>

            <tr>

                <td>

                <%

                if(rank==1){

                    out.print("🥇");

                }else if(rank==2){

                    out.print("🥈");

                }else if(rank==3){

                    out.print("🥉");

                }else{

                    out.print(rank);

                }

                %>

                </td>

                <td><%=p.getProductName()%></td>

                <td><%=p.getTotalSold()%></td>

            </tr>

            <%

                rank++;

                }

            }else{

            %>

            <tr>

                <td colspan="3" class="text-center">

                    No Sales Yet

                </td>

            </tr>

            <%

            }

            %>

            </tbody>

        </table>

    </div>

</div>

</div>

</div>

</div>

<!-- Recent Orders -->

<div class="card shadow mb-5">

<div class="card-header bg-dark text-white">

<h4>

<i class="bi bi-clock-history"></i>

Recent Orders

</h4>

</div>

<div class="card-body">

<table class="table table-hover table-bordered">

<thead class="table-primary">

<tr>

<th>Order ID</th>

<th>Customer</th>

<th>Total</th>

<th>Status</th>

<th>Date</th>

</tr>

</thead>

<tbody>

<%

if(recentOrders!=null && !recentOrders.isEmpty()){

for(RecentOrder order : recentOrders){

%>

<tr>

<td>#<%=order.getOrderId()%></td>

<td><%=order.getCustomerName()%></td>

<td>₹ <%=order.getTotalAmount()%></td>

<td>

<%

String status=order.getOrderStatus();

if(status.equalsIgnoreCase("Pending")){

%>

<span class="badge bg-warning text-dark">Pending</span>

<%

}else if(status.equalsIgnoreCase("Delivered")){

%>

<span class="badge bg-success">Delivered</span>

<%

}else if(status.equalsIgnoreCase("Shipped")){

%>

<span class="badge bg-info">Shipped</span>

<%

}else{

%>

<span class="badge bg-secondary">

<%=status%>

</span>

<%

}

%>

</td>

<td>

<%=order.getOrderDate()%>

</td>

</tr>

<%

}

}else{

%>

<tr>

<td colspan="5" class="text-center">

No Recent Orders Found

</td>

</tr>

<%

}

%>

</tbody>

</table>

</div>

</div>

<!-- Management Section -->

<div class="row">
<!-- Manage Products -->

<div class="col-lg-6 mb-4">

    <div class="card manage-card h-100">

        <div class="card-body">

            <h3>

                <i class="bi bi-box-seam text-primary"></i>

                Manage Products

            </h3>

            <p>

                Add, Update and Delete Products.

            </p>

            <a class="btn btn-primary"

               href="<%=request.getContextPath()%>/viewProducts">

                View Products

            </a>

        </div>

    </div>

</div>

<!-- Manage Categories -->

<div class="col-lg-6 mb-4">

    <div class="card manage-card h-100">

        <div class="card-body">

            <h3>

                <i class="bi bi-folder2-open text-success"></i>

                Manage Categories

            </h3>

            <p>

                Add and Manage Product Categories.

            </p>

            <a class="btn btn-success"

               href="<%=request.getContextPath()%>/categories">

                Open

            </a>

        </div>

    </div>

</div>

<!-- Customers -->

<div class="col-lg-6 mb-4">

    <div class="card manage-card h-100">

        <div class="card-body">

            <h3>

                <i class="bi bi-people-fill text-warning"></i>

                View Customers

            </h3>

            <p>

                See all registered customers.

            </p>

            <a class="btn btn-warning"

               href="<%=request.getContextPath()%>/customers">

                Open

            </a>

        </div>

    </div>

</div>

<!-- Orders -->

<div class="col-lg-6 mb-4">

    <div class="card manage-card h-100">

        <div class="card-body">

            <h3>

                <i class="bi bi-cart-check-fill text-info"></i>

                Manage Orders

            </h3>

            <p>

                View and Manage Customer Orders.

            </p>

            <a class="btn btn-info text-white"

               href="<%=request.getContextPath()%>/adminOrders">

                Open

            </a>

        </div>

    </div>

</div>

</div>

<!-- Quick Actions -->

<div class="row mt-4">

<div class="col-md-4 mb-3">

<div class="card shadow-sm text-center p-4">

<h1 class="text-primary">

<i class="bi bi-box2-heart"></i>

</h1>

<h5>

Inventory

</h5>

<p>

Manage all products and stock.

</p>

</div>

</div>

<div class="col-md-4 mb-3">

<div class="card shadow-sm text-center p-4">

<h1 class="text-success">

<i class="bi bi-graph-up-arrow"></i>

</h1>

<h5>

Business Growth

</h5>

<p>

Track sales and customer activity.

</p>

</div>

</div>

<div class="col-md-4 mb-3">

<div class="card shadow-sm text-center p-4">

<h1 class="text-danger">

<i class="bi bi-shield-lock-fill"></i>

</h1>

<h5>

Secure Admin

</h5>

<p>

Protected admin operations.

</p>

</div>

</div>

</div>

<!-- Logout -->

<div class="text-center mt-5">

<a class="btn btn-danger btn-lg"

href="<%=request.getContextPath()%>/adminlogout">

<i class="bi bi-box-arrow-right"></i>

Logout

</a>

</div>

</div>

<footer>

<div class="container">

<h4>

JavaCart Admin Panel

</h4>

<p>

Manage Products • Categories • Customers • Orders

</p>

<hr>

<p>

© 2026 JavaCart | All Rights Reserved.

</p>

</div>

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>