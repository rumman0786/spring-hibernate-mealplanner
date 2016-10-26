<%@ page import="net.therap.mealplanner.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 10/25/16
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Meal Planner- Therap</title>

    <!-- Bootstrap core CSS -->
    <link href="/idea-jsp-servlet-tomcat-example/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/idea-jsp-servlet-tomcat-example/statics/css/signin.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <%  User user = (User) request.getAttribute("user"); %>

    <form class="form-signin" method="post" action="/idea-jsp-servlet-tomcat-example/edit-user">
    <%--<form class="form-signin" method="post" action="/login">--%>
        <h2 class="form-signin-heading">Edit User Information</h2>

        <label for="username" class="sr-only">Username</label>
        <input type="text" id="username" class="form-control" placeholder="Username" name="username" value="<%out.print(user.getUsername());%>" required autofocus>

        <label for="first_name" class="sr-only">First Name</label>
        <input type="text" id="first_name" class="form-control" placeholder="First Name" name="first_name" value="<%out.print(user.getFirstName());%>">

        <label for="last_name" class="sr-only">Last Name</label>
        <input type="text" id="last_name" class="form-control" placeholder="Last Name" name="last_name" value="<%out.print(user.getLastName());%>">

        <%--<label for="password" class="sr-only">Password</label>--%>
        <%--<input type="password" id="password" class="form-control"  name="password" placeholder="Password" required>--%>
        <%--Add a second password field and match with this--%>

        <label for="email" class="sr-only">Email</label>
        <input type="email" id="email" class="form-control" placeholder="Email" name="email" required value="<%out.print(user.getEmail());%>">

        <input type="hidden" id="user_id" name="user_id" value="<%out.print(user.getId());%>" required>
        <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
    </form>

</div> <!-- /container -->
</body>
</html>
