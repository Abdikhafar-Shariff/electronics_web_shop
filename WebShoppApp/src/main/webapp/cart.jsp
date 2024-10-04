<%--
  Created by IntelliJ IDEA.
  User: kuler
  Date: 2024-10-01
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>;
<%@ page import="java.util.List" %>
<%@ page import="ui.CartItemInfo" %>
<%@ page import="bl.CartHandler" %>
<%@ page import="ui.CartInfo" %>
<html>
<head>
    <title>Cart</title>
    <%@include file="includes/header.jsp"%>

</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
<div class="d-flex py-3">
<%
            // Initialize total sum variable
            float totalSum = 0;

            // Fetch the cart items from the request attribute
            CartInfo cartInfo = new CartInfo();
            int cartId = cartInfo.getCartId();
            List<CartItemInfo> cartItems = CartHandler.getAllCartItem(cartId);

            if (cartItems != null && !cartItems.isEmpty()) {
                for (CartItemInfo item : cartItems) {
                    totalSum += item.getPrice() * item.getQuantity();
                }
            }
        %>
<h3>Total Price: Kr </h3><a class ="mx-3  btn btn-primary" href="#"> Check Out</a></div>
<table class="table table-light">
<thead>
<tr>
<th scope="col">Name</th>
<th scope="col">Category</th>
<th scope="col">Price</th>
<th scope="col">Buy Now</th>
<th scope="col">Cancel</th>
</tr>
</thead>
<tbody>
<%
       if (cartItems != null && !cartItems.isEmpty()) {
           for (CartItemInfo item : cartItems) {
            %>
                        <tr>
                            <td><%= item.getItemName() %></td>
                            <td>Kr <%= item.getPrice() %></td>
                            <td><%= item.getQuantity() %></td>
<td>
<form action="cart" method="post" class="form-inline">
<input type="hidden" name="id" value="1" class="form-input">
<div class="form-group d-flex justify-content-between">
<a class="btn btn-sm btn-decre" href=""><i class="fas fa-minus-square"></i></a>
<input type="text" name="quantity" class="from-control" value="1" readonly>
<a class="btn btn-sm btn-incre" href=""><i class="fas fa-plus-square"></i></a>
</div>
</form>
</td>
<td <a class="btn btn-sm btn-danger"></a> Remove</td>
</tr>
<%
}
} else{
  %>
                    <tr>
                        <td colspan="6">Your cart is empty.</td>
                    </tr>
            <%
                }
            %>
</tbody>
</table>
</d>

<%@include file="includes/footer.jsp"%>
</body>
</html>
