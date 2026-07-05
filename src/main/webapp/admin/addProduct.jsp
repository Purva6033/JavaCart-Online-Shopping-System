<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>

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
    margin:30px auto;
    background:white;
    padding:25px;
    border-radius:10px;
    box-shadow:0px 0px 10px gray;

}

h2{

    text-align:center;
    color:#2c3e50;

}

input, textarea, select{

    width:100%;
    padding:10px;
    margin-top:8px;
    margin-bottom:15px;
    box-sizing:border-box;

}

input[type=submit]{

    background:#007bff;
    color:white;
    border:none;
    cursor:pointer;
    font-size:16px;

}

input[type=submit]:hover{

    background:#0056b3;

}

</style>

</head>

<body>

<div class="container">

<h2>Add New Product</h2>

<form action="${pageContext.request.contextPath}/addProduct" method="post">

<label>Product Name</label>

<input type="text" name="productName" required>

<label>Description</label>

<textarea name="description" rows="4" required></textarea>

<label>Category</label>

<select name="category">

<option>Electronics</option>

<option>Fashion</option>

<option>Books</option>

<option>Furniture</option>

<option>Sports</option>

</select>

<label>Price</label>

<input type="number" step="0.01" name="price" required>

<label>Stock Quantity</label>

<input type="number" name="stockQuantity" required>

<label>Image Name</label>

<input type="text" name="imageUrl" placeholder="example.jpg">

<input type="submit" value="Add Product">

</form>

</div>

</body>
</html>