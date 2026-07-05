<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaCart - Login</title>

<style>
body{
    background:
        linear-gradient(rgba(255,255,255,0.70),
                        rgba(255,255,255,0.70)),
        url("../images/background.jpg");

    background-size:500px;
    background-repeat:repeat;
    background-attachment:fixed;
}

.container{

    width:350px;
    margin:80px auto;
    background:white;
    padding:25px;
    border-radius:10px;
    box-shadow:0px 0px 10px gray;

}

h2{
    text-align:center;
    color:#2c3e50;
}

input{

    width:100%;
    padding:10px;
    margin-top:10px;
    margin-bottom:15px;

}

button{

    width:100%;
    padding:10px;
    background:#007bff;
    color:white;
    border:none;
    cursor:pointer;
    font-size:16px;

}

button:hover{

    background:#0056b3;

}

a{

    text-decoration:none;

}

</style>

</head>

<body>

<div class="container">

<h2>JavaCart Login</h2>

<form action="${pageContext.request.contextPath}/login" method="post">

<label>Email</label>

<input type="email" name="email"  required>

<label>Password</label>

<input type="password" name="password" required>

<button type="submit">Login</button>

<br><br>

<p align="center">

New Customer?

<a href="register.jsp">

Register Here

</a>

</p>

</form>

</div>

</body>
</html>