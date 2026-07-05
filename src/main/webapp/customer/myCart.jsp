<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.javacart.model.CartItem"%>
<%@ page import="com.javacart.model.User"%>

<%
List<CartItem> cartList =
(List<CartItem>)request.getAttribute("cartList");

double total=0;
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>My Cart</title>

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
margin-bottom:30px;

}

table{

width:100%;
background:white;
border-collapse:collapse;
box-shadow:0 0 10px lightgray;

}

th{

background:#2c3e50;
color:white;
padding:15px;

}

td{

padding:15px;
text-align:center;
border-bottom:1px solid #ddd;

}

img{

width:100px;
height:100px;

}

.remove{

background:red;
color:white;
padding:8px 15px;
text-decoration:none;
border-radius:5px;

}

.total{

margin-top:20px;
font-size:25px;
font-weight:bold;
text-align:right;

}

.checkout{

display:inline-block;
margin-top:20px;
padding:12px 20px;
background:green;
color:white;
text-decoration:none;
border-radius:5px;
float:right;

}

</style>

</head>

<body>

<h2>My Shopping Cart</h2>

<table>

<tr>

<th>Image</th>

<th>Product</th>

<th>Price</th>

<th>Quantity</th>

<th>Subtotal</th>

<th>Action</th>

</tr>

<%

if(cartList!=null){

for(CartItem item : cartList){

double subtotal=item.getPrice()*item.getQuantity();

total+=subtotal;

%>

<tr>

<td>

<img src="<%=request.getContextPath()%>/images/<%=item.getImageUrl()%>">

</td>

<td>

<%=item.getProductName()%>

</td>

<td>

₹ <%=item.getPrice()%>

</td>

<td>

<td>

<a
href="<%=request.getContextPath()%>/decreaseQuantity?cartId=<%=item.getCartId()%>"
style="text-decoration:none;
padding:5px 10px;
background:red;
color:white;
border-radius:5px;">

-

</a>

&nbsp;

<b>

<%=item.getQuantity()%>

</b>

&nbsp;

<a
href="<%=request.getContextPath()%>/increaseQuantity?cartId=<%=item.getCartId()%>"
style="text-decoration:none;
padding:5px 10px;
background:green;
color:white;
border-radius:5px;">

+

</a>

</td>

</td>

<td>

₹ <%=subtotal%>

</td>

<td>

<a class="remove" href="<%=request.getContextPath()%>/removeCart?cartId=<%=item.getCartId()%>">Remove </a>

</td>

</tr>

<%

}

}

%>

</table>

<div class="total">

Grand Total :

₹ <%=total%>

</div>

<br><br>

<a class="checkout"
href="<%=request.getContextPath()%>/checkout"> Proceed To Checkout </a>


</body>
</html>