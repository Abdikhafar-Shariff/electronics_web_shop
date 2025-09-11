<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="ui.CartItemInfo" %>
<%@ page import="bl.CartHandler" %>
<%@ page import="bl.CartItem" %>
<html>
<head>
    <title>Cart</title>
    <%@include file="includes/header.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <div class="d-flex py-3">
    <div class="container">
        <div class="d-flex py-3">
        <%
            // Assuming the cartList (List<CartItem>) is stored in session
            List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartList");
            List<CartItem> cartItemInfoList = null;

            // If the cart list exists, call getAllCartItem() to fetch detailed info
            if (cartItems != null && !cartItems.isEmpty()) {
                try {
                    // Get detailed cart items info
                    cartItemInfoList = CartHandler.getAllCartItem(cartItems);
                    session.setAttribute("cartList", cartItems);
                } catch (SQLException e) {
                    e.printStackTrace(); // handle the exception appropriately
                }
            }

            // If the cart is not empty, display the items
            if (cartItems != null && !cartItems.isEmpty()) {
        %>
        <h3>Total Price: Kr </h3><a class="mx-3 btn btn-primary" href="#"> Check Out</a></div>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Buy Now</th>
                    <th scope="col">Cancel</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Loop through each cart item and display it
                    for (CartItem item : cartItems) {
                %>
                <tr>
                    <td><%= item.getItemName() %></td>
                    <td>Kr <%= item.getPrice() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>
                        <form action="" method="post" class="form-inline">
                            <input type="hidden" name="item_id" value="<%= item.getItemId() %>" class="form-input">
                            <button type="submit" class="btn btn-sm btn-primary">Buy Now</button>
                        </form>
                    </td>
                    <td>
                        <a href="" class="btn btn-sm btn-danger">Remove</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <tr>
            <td colspan="5">Your cart is empty.</td>
        </tr>
    <%
        }
    %>
</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>
