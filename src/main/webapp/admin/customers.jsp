<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.User"%>

<%
List<User> userList = (List<User>) request.getAttribute("userList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Customers</title>

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
}

table{
    width:100%;
    border-collapse:collapse;
    background:white;
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

.back{
    display:inline-block;
    margin-top:20px;
    padding:10px 20px;
    background:green;
    color:white;
    text-decoration:none;
    border-radius:5px;
}

</style>

</head>

<body>

<h2>Registered Customers</h2>

<table>

<tr>
    <th>ID</th>
    <th>Full Name</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Address</th>
</tr>

<%
if(userList != null){

    for(User user : userList){
%>

<tr>

<td><%=user.getUserId()%></td>
<td><%=user.getFullName()%></td>
<td><%=user.getEmail()%></td>
<td><%=user.getPhone()%></td>
<td><%=user.getAddress()%></td>

</tr>

<%
    }
}
%>

</table>

<br>

<a class="back"
href="<%=request.getContextPath()%>/admin/dashboard.jsp">

Back to Dashboard

</a>

</body>
</html>