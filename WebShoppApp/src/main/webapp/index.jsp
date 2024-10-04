<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bl.ItemHandler" %>
<%@ page import="java.util.List" %>
<%@ page import="ui.ItemInfo" %>

<%

    List<ItemInfo> items = ItemHandler.getAllItems();
%>

<html>
<head>
    <title>Web shop</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        /* För att säkerställa att alla kort har samma höjd */
        .card {
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
        }

        .card img {
            object-fit: cover;
            height: 150px;
        }

        .card-body {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .price, .category {
            font-size: 14px;
            color: #555;
        }

        .card-title {
            font-size: 16px;
            font-weight: bold;
        }

        /* Gör att alla kort är lika stora */
        .card-container {
            display: flex;
            flex-wrap: wrap;
        }

        .card-container .col-md-4 {
            padding: 15px;
        }
    </style>
    <%@include file="includes/header.jsp"%>
    <%@include file="includes/navbar.jsp"%>
</head>
<body>

<div class="container">
    <div class="card-header my-3">All Products</div>
    <div class="row card-container">
        <%
            if(!items.isEmpty()) {
                for(ItemInfo p: items) { %>
                    <div class="col-md-4">
                        <div class="card">
                            <img class="card-img-top" src="<%=p.getImage_url()%>" alt="Product Image">
                            <div class="card-body">
                                <h5 class="card-title"><%=p.getItemName()%></h5>
                                <h6 class="category"><%=p.getCategory()%></h6>
                                <h6 class="price">$<%=p.getPrice()%></h6>
                                <div class="mt-3 d-flex justify-content-between">
                                    <a href="cart?action=add&itemId=<%= p.getItemId() %>" class="btn btn-primary">Add to Cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                <% }
            } else { %>
            <div class="col-md-12">
                <p>No products available.</p>
            </div>
        <% } %>
    </div>
</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>