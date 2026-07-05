<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.javacart.model.User"%>

<%
User user = (User)request.getAttribute("user");

if(user==null){

    response.sendRedirect(request.getContextPath()+"/customer/login.jsp");
    return;

}

String success=request.getParameter("success");
String error=request.getParameter("error");

String passwordSuccess=request.getParameter("passwordSuccess");
String passwordError=request.getParameter("passwordError");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>My Profile</title>

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
.profile-card{

background:white;
padding:30px;
border-radius:15px;
box-shadow:0 5px 15px rgba(0,0,0,.1);

}

.section-title{

font-weight:bold;
margin-bottom:20px;

}

.readonly{

background:#ececec;

}

footer{

background:#212529;
color:white;
padding:20px;
margin-top:50px;
text-align:center;

}

</style>

</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

<div class="container">

<a class="navbar-brand"
href="<%=request.getContextPath()%>/customerHome">

🛒 JavaCart

</a>

<button class="navbar-toggler"
type="button"
data-bs-toggle="collapse"
data-bs-target="#navbar">

<span class="navbar-toggler-icon"></span>

</button>

<div class="collapse navbar-collapse"
id="navbar">

<ul class="navbar-nav ms-auto">

<li class="nav-item">

<a class="nav-link"
href="<%=request.getContextPath()%>/customerHome">

Home

</a>

</li>

<li class="nav-item">

<a class="nav-link"
href="<%=request.getContextPath()%>/myCart">

My Cart

</a>

</li>

<li class="nav-item">

<a class="nav-link"
href="<%=request.getContextPath()%>/myOrders">

My Orders

</a>

</li>

<li class="nav-item">

<a class="nav-link active"
href="<%=request.getContextPath()%>/profile">

My Profile

</a>

</li>

<li class="nav-item">

<a class="nav-link text-danger"
href="<%=request.getContextPath()%>/logout">

Logout

</a>

</li>

</ul>

</div>

</div>

</nav>

<div class="container mt-5">

<div class="row justify-content-center">

<div class="col-md-8">

<div class="profile-card">

<h2 class="section-title">

<i class="bi bi-person-circle"></i>

My Profile

</h2>

<%

if(success!=null){

%>

<div class="alert alert-success">

Profile Updated Successfully.

</div>

<%

}

%>

<%

if(error!=null){

%>

<div class="alert alert-danger">

Unable to Update Profile.

</div>

<%

}

%>

<form
action="<%=request.getContextPath()%>/profile"
method="post">

<div class="mb-3">

<label class="form-label">

Full Name

</label>

<input
type="text"
name="fullName"
class="form-control"
value="<%=user.getFullName()%>"
required>

</div>

<div class="mb-3">

<label class="form-label">

Email

</label>

<input
type="email"
class="form-control readonly"
value="<%=user.getEmail()%>"
readonly>

</div>

<div class="mb-3">

<label class="form-label">

Phone

</label>

<input
type="text"
name="phone"
class="form-control"
value="<%=user.getPhone()%>"
required>

</div>

<div class="mb-3">

<label class="form-label">

Address

</label>

<textarea
name="address"
class="form-control"
rows="4"
required><%=user.getAddress()%></textarea>

</div>

<div class="text-center">

<button
class="btn btn-primary btn-lg">

<i class="bi bi-pencil-square"></i>

Update Profile

</button>

</div>

</form>

<hr class="my-5">
<h3 class="section-title">

<i class="bi bi-lock-fill"></i>

Change Password

</h3>

<%

if(passwordSuccess!=null){

%>

<div class="alert alert-success">

<%=passwordSuccess%>

</div>

<%

}

%>

<%

if(passwordError!=null){

%>

<div class="alert alert-danger">

<%=passwordError%>

</div>

<%

}

%>

<form
action="<%=request.getContextPath()%>/changePassword"
method="post">

<div class="mb-3">

<label class="form-label">

Current Password

</label>

<input
type="password"
name="currentPassword"
class="form-control"
required>

</div>

<div class="mb-3">

<label class="form-label">

New Password

</label>

<input
type="password"
name="newPassword"
class="form-control"
required>

</div>

<div class="mb-3">

<label class="form-label">

Confirm Password

</label>

<input
type="password"
name="confirmPassword"
class="form-control"
required>

</div>

<div class="text-center">

<button
class="btn btn-success btn-lg">

<i class="bi bi-key-fill"></i>

Change Password

</button>

</div>

</form>

<div class="text-center mt-5">

<a
href="<%=request.getContextPath()%>/customerHome"
class="btn btn-secondary">

<i class="bi bi-arrow-left-circle"></i>

Back To Shopping

</a>

</div>

</div>

</div>

</div>

</div>

<footer>

<div class="container">

<h5>

JavaCart

</h5>

<p>

Manage your profile, password and shopping account securely.

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