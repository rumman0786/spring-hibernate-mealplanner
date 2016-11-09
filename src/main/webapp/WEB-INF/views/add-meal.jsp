<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <form class="form-signin" method="post" action="/admin/add-meal">
                <h2 class="form-signin-heading">Add a Meal</h2>

                <div class="form-group">
                    <label for="mealname" class="sr-only">Meal Name</label>
                    <input type="text" id="mealname" class="form-control" placeholder="Meal Name" name="mealname"
                           required="required" autofocus="autofocus"/>
                </div>

                <div class="form-group">
                    <label for="menu_type" class="sr-only">Menu Type</label>
                    <select class="form-control" id="menu_type" name="menu_type" path="menuType" required="required">
                        <option value="">Select a Menu Type</option>
                        <c:forEach var="menuType" items="${menuTypes}">
                            <option value="${menuType.id}">${menuType.category}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="day" class="sr-only">Day of Week</label>
                    <select class="form-control" id="day" name="day" path="day" required="required">
                        <option value="">Select a Day</option>
                        <option value="SATURDAY">SATURDAY</option>
                        <option value="SUNDAY">SUNDAY</option>
                        <option value="MONDAY">MONDAY</option>
                        <option value="TUESDAY">TUESDAY</option>
                        <option value="WEDNESDAY">WEDNESDAY</option>
                        <option value="THURSDAY">THURSDAY</option>
                        <option value="FRIDAY">FRIDAY</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="dish_list" class="sr-only">Dishes</label>
                    <select class="form-control" id="dish_list" name="dish_list" path="dishSet" multiple="multiple"
                            required="required">
                        <c:forEach var="dish" items="${dishLish}">
                            <option value="${dish.id}">${dish.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
            </form>
        </div>
    </div>
</div>
<!-- /container -->
<%@ include file="footer.jsp" %>
</body>
</html>
