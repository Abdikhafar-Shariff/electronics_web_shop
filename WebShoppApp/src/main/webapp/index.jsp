<%@ page contentType="text/html;charset=UTF-8" language="java" %>;
<%@page import="db.DatabaseConnection" %>
<%@ page import="db.ProductsDB" %>
<%@ page import="java.util.List" %>
<%@ page import="bl.Products" %>
;
<%
    ProductsDB productsDB = new ProductsDB(DatabaseConnection.getConnection());
    List<Products> products =productsDB.getAllProducts();
%>
<html>
<head>
<title>Web shop</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
<%--<% out.println(DatabaseConnection.getConnection());%>--%>
<div class="container">
    <div class="card-header my-3"> All Products</div>
    <div class="row">
        <%
            if(!products.isEmpty()){
                for(Products p: products){%>
                     <div class="col-md-2 my-3">
        <div class="card w-100" style="width: 18rem;">
            <img class="card-img-top" src="product_img/<%= p.getImage_url()%>" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title"><%=p.getName()%></h5>
                <h6 class="price"><%=p.getPrice()%></h6>
                <h6 class="category"><%=p.getCategory_id()%></h6>
                <div class="mt-3 d-flex justify-content-between">
                    <a href="#" class="btn btn-primary">Add to Cart</a>
                </div>

            </div>
        </div>
    </div>
                <%}
            }%>

    </div>

</div>
<%@include file="includes/footer.jsp"%>
</body>
</html>
