<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.javacart.model.Product"%>

<%
Product product = (Product) request.getAttribute("product");

if(product == null){
    response.sendRedirect(request.getContextPath() + "/viewProducts");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>

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
    width:500px;
    margin:40px auto;
    background:white;
    padding:25px;
    border-radius:10px;
    box-shadow:0px 0px 10px gray;
}

h2{
    text-align:center;
    color:#2c3e50;
}

label{
    font-weight:bold;
}

input,
textarea,
select{
    width:100%;
    padding:10px;
    margin-top:8px;
    margin-bottom:15px;
    box-sizing:border-box;
}

input[type=submit]{
    background:#28a745;
    color:white;
    border:none;
    cursor:pointer;
    font-size:16px;
}

input[type=submit]:hover{
    background:#218838;
}

.back{
    display:inline-block;
    margin-top:10px;
    text-decoration:none;
    background:#007bff;
    color:white;
    padding:10px 15px;
    border-radius:5px;
}

.back:hover{
    background:#0056b3;
}

</style>

</head>

<body>

<div class="container">

<h2>Edit Product</h2>

<form action="<%=request.getContextPath()%>/updateProduct" method="post">

    <!-- Hidden Product ID -->
    <input type="hidden"
           name="productId"
           value="<%=product.getProductId()%>">

    <label>Product Name</label>

    <input type="text"
           name="productName"
           value="<%=product.getProductName()%>"
           required>

    <label>Description</label>

    <textarea name="description"
              rows="4"
              required><%=product.getDescription()%></textarea>

    <label>Category</label>

    <select name="category">

        <option value="Electronics"
        <%=product.getCategory().equals("Electronics") ? "selected" : ""%>>
        Electronics
        </option>

        <option value="Fashion"
        <%=product.getCategory().equals("Fashion") ? "selected" : ""%>>
        Fashion
        </option>

        <option value="Books"
        <%=product.getCategory().equals("Books") ? "selected" : ""%>>
        Books
        </option>

        <option value="Furniture"
        <%=product.getCategory().equals("Furniture") ? "selected" : ""%>>
        Furniture
        </option>

        <option value="Sports"
        <%=product.getCategory().equals("Sports") ? "selected" : ""%>>
        Sports
        </option>

    </select>

    <label>Price</label>

    <input type="number"
           step="0.01"
           name="price"
           value="<%=product.getPrice()%>"
           required>

    <label>Stock Quantity</label>

    <input type="number"
           name="stockQuantity"
           value="<%=product.getStockQuantity()%>"
           required>

    <label>Image Name</label>

    <input type="text"
           name="imageUrl"
           value="<%=product.getImageUrl()%>">

    <input type="submit"
           value="Update Product">

</form>

<br>

<a class="back"
href="<%=request.getContextPath()%>/viewProducts">
Back to Products
</a>

</div>

</body>
</html>