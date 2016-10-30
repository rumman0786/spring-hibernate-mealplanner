<%@ page import="net.therap.mealplanner.entity.Meal" %>
<%@ page import="net.therap.mealplanner.entity.MenuType" %>
<%@ page import="java.util.List" %>
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

            <% Meal meal = (Meal) request.getAttribute("meal"); %>

            <form class="form-signin" method="post" action="<% out.print(request.getContextPath());%>/edit-meal">
                <%--<form class="form-signin" method="post" action="/login">--%>
                <h2 class="form-signin-heading">Add a Meal</h2>

                <div class="form-group">
                    <label for="mealname" class="sr-only">Meal Name</label>
                    <input type="text" id="mealname" class="form-control" placeholder="Meal Name" name="mealname"
                           value="<%out.print(meal.getName());%>" required autofocus>
                </div>

                <% List<MenuType> menuTypeList = (List<MenuType>) request.getAttribute("menuTypes"); %>
                <div class="form-group">
                    <label for="menu_type" class="sr-only">Menu Type</label>
                    <select class="form-control" id="menu_type" name="menu_type" required>
                        <option value="">Select a Menu Type</option>
                        <% for (MenuType menuType : menuTypeList) { %>
                        <option value="<% out.print(menuType.getId());%>" <% if (meal.getMenuType().equals(menuType) ) { %> selected="selected" <% } %> > <% out.print(menuType.getCategory()); %></option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="day" class="sr-only">Day of Week</label>
                    <select class="form-control" id="day" name="day" required>
                        <option value="">Select a Day</option>
                        <option value="SATURDAY" <% if (meal.getDay().equals("SATURDAY") ) { %> selected="selected" <% } %> >SATURDAY</option>
                        <option value="SUNDAY" <% if (meal.getDay().equals("SUNDAY") ) { %> selected="selected" <% } %> >SUNDAY</option>
                        <option value="MONDAY" <% if (meal.getDay().equals("MONDAY") ) { %> selected="selected" <% } %> >MONDAY</option>
                        <option value="TUESDAY" <% if (meal.getDay().equals("TUESDAY") ) { %> selected="selected" <% } %> >TUESDAY</option>
                        <option value="WEDNESDAY" <% if (meal.getDay().equals("WEDNESDAY") ) { %> selected="selected" <% } %> >WEDNESDAY</option>
                        <option value="THURSDAY" <% if (meal.getDay().equals("THURSDAY") ) { %> selected="selected" <% } %> >THURSDAY</option>
                        <option value="FRIDAY" <% if (meal.getDay().equals("FRIDAY") ) { %> selected="selected" <% } %> >FRIDAY</option>
                    </select>
                </div>

                <% List<Dish> dishList = (List<Dish>) request.getAttribute("dishLish"); %>
                <div class="form-group">
                    <label for="dish_list" class="sr-only">Dishes</label>
                    <select class="form-control" id="dish_list" name="dish_list" multiple required>
                        <% for (Dish dish : dishList) { %>
                        <option value="<% out.print(dish.getId());%>" <% if (meal.getDishSet().contains(dish) ) { %> selected="selected" <% } %> ><% out.print(dish.getName()); %></option>
                        <% } %>
                    </select>
                </div>

                <input type="hidden" id="meal_id" name="meal_id" value="<%out.print(meal.getId());%>" required>

                <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
            </form>


        </div>
    </div>
</div> <!-- /container -->
<%@ include file="footer.jsp" %>
</body>
</html>
