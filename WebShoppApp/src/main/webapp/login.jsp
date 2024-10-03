<%--
  Created by IntelliJ IDEA.
  User: kuler
  Date: 2024-10-03
  Time: 01:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <%@include file="includes/header.jsp"%>
  <%@include file ="includes/navbar.jsp"%>
  <div class="container">
  <div class="card w-50 mx-auto my-5">
  <div class="card-header text-center">User Login</div>
  <div class="card-body">
  <form action="" method="post">
  <div class="form-group">
  <label>Email Address</label>
  <input type="email" class="form-control" name="login-email" placeholder="Enter your email" required>
  </div>
    <div class="form-group">
    <label>Password</label>
    <input type="password" class="form-control" name="login-password" placeholder="***********" required>
    </div>
    <div class = "text-center">
    <button type="submit" class="bt btn-primary">Login</div>
    </div>
  </form>
  </d>
  </div>
  </div>
  <%@include file="includes/footer.jsp"%>
  </body>
</html>
