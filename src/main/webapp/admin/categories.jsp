<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.Category"%>

<%
List<Category> categoryList =
(List<Category>)request.getAttribute("categoryList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Categories</title>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:Arial;
}

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

width:80%;
margin:auto;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0px 0px 10px lightgray;

}

h1{
margin-bottom:20px;
}

form{
margin-bottom:30px;
}

input{

padding:10px;
width:300px;

}

button{

padding:10px 20px;
background:#007bff;
color:white;
border:none;
cursor:pointer;

}

button:hover{

background:#0056b3;

}

table{

width:100%;
border-collapse:collapse;

}

th,td{

padding:12px;
border:1px solid lightgray;
text-align:center;

}

th{

background:#2c3e50;
color:white;

}

.delete{

background:red;
color:white;
padding:8px 15px;
text-decoration:none;
border-radius:5px;

}

.delete:hover{

background:darkred;

}

.back{

display:inline-block;
margin-top:20px;
background:green;
color:white;
padding:10px 20px;
text-decoration:none;
border-radius:5px;

}

</style>

</head>

<body>

<div class="container">

<h1>Manage Categories</h1>

<form action="<%=request.getContextPath()%>/addCategory"
method="post">

<input type="text"
name="categoryName"
placeholder="Enter Category Name"
required>

<button type="submit">

Add Category

</button>

</form>

<table>

<tr>

<th>ID</th>
<th>Category Name</th>
<th>Action</th>

</tr>

<%

if(categoryList!=null){

for(Category category : categoryList){

%>

<tr>

<td>

<%=category.getCategoryId()%>

</td>

<td>

<%=category.getCategoryName()%>

</td>

<td>

<a class="delete"

href="<%=request.getContextPath()%>/deleteCategory?id=<%=category.getCategoryId()%>">Delete </a>

</td>

</tr>

<%

}

}

%>

</table>

<a class="back"
href="<%=request.getContextPath()%>/admin/dashboard.jsp"> ← Back To Dashboard </a>

</div>

</body>
</html>