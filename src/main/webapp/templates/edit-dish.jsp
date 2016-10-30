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
<%@ include file="header.jsp" %>

<body>
<%@ include file="topbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <% Dish dish = (Dish) request.getAttribute("dish"); %>

            <form class="form-signin" method="post" action="<% out.print(request.getContextPath());%>/admin/edit-dish">
                <%--<form class="form-signin" method="post" action="/login">--%>
                <h2 class="form-signin-heading">Add a Dish</h2>

                <label for="dishname" class="sr-only">Dish Name</label>
                <input type="text" id="dishname" class="form-control" placeholder="Dish Name" name="dishname"
                       value="<% out.print(dish.getName()); %>" required autofocus>

                <label for="calories" class="sr-only">Calories</label>
                <input type="text" id="calories" class="form-control" name="calories" placeholder="Calories"
                       value="<% out.print(dish.getCalories()); %>" required>

                <input type="hidden" id="dish_id" name="dish_id" value="<%out.print(dish.getId());%>" required>
                <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
            </form>
        </div>
    </div>
</div>
<!-- /container -->
<%@ include file="footer.jsp" %>
</body>
</html>
