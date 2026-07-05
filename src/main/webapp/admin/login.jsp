<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Admin Login</title>

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
.container{
    width:350px;
    margin:100px auto;
    background:white;
    padding:30px;
    border-radius:10px;
    box-shadow:0 0 10px gray;
}

h2{
    text-align:center;
    color:#333;
}

input[type=email],
input[type=password]{

    width:100%;
    padding:10px;
    margin-top:5px;
    margin-bottom:15px;
    box-sizing:border-box;
}

input[type=submit]{

    width:100%;
    padding:10px;
    background:#007bff;
    color:white;
    border:none;
    cursor:pointer;
    font-size:16px;
}

input[type=submit]:hover{

    background:#0056b3;
}

.error{

    color:red;
    text-align:center;
    margin-bottom:15px;
}

</style>

</head>

<body>

<div class="container">

<h2>Admin Login</h2>

<%
String error=(String)request.getAttribute("error");

if(error!=null){
%>

<p class="error"><%=error %></p>

<%
}
%>

<form action="${pageContext.request.contextPath}/adminlogin" method="post">

<label>Email</label>

<input type="email" name="email" required>

<label>Password</label>

<input type="password" name="password" required>

<input type="submit" value="Login">

</form>

</div>

</body>
</html>