<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.Product"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Products</title>

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
    color:#2c3e50;
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
    background:#007bff;
    color:white;
}

tr:nth-child(even){
    background:#f9f9f9;
}

a{
    text-decoration:none;
    color:white;
}

.edit{
    background:green;
    padding:8px 12px;
    border-radius:5px;
}

.delete{
    background:red;
    padding:8px 12px;
    border-radius:5px;
}

.add{
    display:inline-block;
    margin-bottom:20px;
    background:#007bff;
    color:white;
    padding:10px 20px;
    border-radius:5px;
}

.add:hover{
    background:#0056b3;
}

.edit:hover{
    background:darkgreen;
}

.delete:hover{
    background:darkred;
}

</style>

</head>

<body>

<h2>Product List</h2>

<a class="add"
href="<%=request.getContextPath()%>/admin/addProduct.jsp">
Add New Product
</a>

<table>

<tr>
    <th>ID</th>
    <th>Product Name</th>
    <th>Description</th>
    <th>Category</th>
    <th>Price</th>
    <th>Stock</th>
    <th>Image</th>
    <th>Edit</th>
    <th>Delete</th>
</tr>

<%

List<Product> productList = (List<Product>) request.getAttribute("productList");

if(productList != null){

    for(Product product : productList){

%>

<tr>

    <td><%=product.getProductId()%></td>

    <td><%=product.getProductName()%></td>

    <td><%=product.getDescription()%></td>

    <td><%=product.getCategory()%></td>

    <td>₹ <%=product.getPrice()%></td>

    <td><%=product.getStockQuantity()%></td>

    <td><%=product.getImageUrl()%></td>

    <td>

        <a class="edit"
        href="<%=request.getContextPath()%>/editProduct?id=<%=product.getProductId()%>">
        Edit
        </a>

    </td>

    <td>

        <a class="delete"
        href="<%=request.getContextPath()%>/deleteProduct?id=<%=product.getProductId()%>"
        onclick="return confirm('Are you sure you want to delete this product?');">
        Delete
        </a>

    </td>

</tr>

<%

    }

}

else{

%>

<tr>

<td colspan="9">No Products Available</td>

</tr>

<%

}

%>

</table>

</body>
</html>