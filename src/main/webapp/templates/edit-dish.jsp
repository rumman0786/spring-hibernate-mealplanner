<%@ page import="net.therap.mealplanner.entity.Dish" %>
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
    <%  Dish dish = (Dish) request.getAttribute("dish"); %>

    <form class="form-signin" method="post" action="/idea-jsp-servlet-tomcat-example/edit-dish">
    <%--<form class="form-signin" method="post" action="/login">--%>
        <h2 class="form-signin-heading">Add a Dish</h2>

        <label for="dishname" class="sr-only">Dish Name</label>
        <input type="text" id="dishname" class="form-control" placeholder="Dish Name" name="dishname" value="<% out.print(dish.getName()); %>" required autofocus>

        <label for="calories" class="sr-only">Calories</label>
        <input type="text" id="calories" class="form-control"  name="calories" placeholder="Calories" value="<% out.print(dish.getCalories()); %>" required>

        <input type="hidden" id="dish_id" name="dish_id" value="<%out.print(dish.getId());%>" required>
        <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
    </form>

</div> <!-- /container -->
</body>
</html>
