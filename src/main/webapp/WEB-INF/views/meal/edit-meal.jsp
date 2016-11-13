<%@ page import="net.therap.mealplanner.entity.Dish" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 10/25/16
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%@ include file="../header.jsp" %>

<body>
<%@ include file="../topbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="../sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <form class="form-signin" method="post" action="${pageContext.request.contextPath}/admin/edit-meal">
                <h2 class="form-signin-heading">Add a Meal</h2>

                <div class="form-group">
                    <label for="mealname" class="sr-only">Meal Name</label>
                    <input type="text" id="mealname" class="form-control" placeholder="Meal Name" name="mealname"
                           value="${meal.name}" required autofocus>
                </div>

                <div class="form-group">
                    <label for="menu_type" class="sr-only">Menu Type</label>
                    <select class="form-control" id="menu_type" name="menu_type" required>
                        <option value="">Select a Menu Type</option>
                        <c:forEach var="menuType" items="${menuTypes}">
                            <option value="${menuType.id}" <c:if
                                    test="${menuType.id == meal.menuType.id}"> selected="selected" </c:if> >${menuType.category}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="day" class="sr-only">Day of Week</label>
                    <select class="form-control" id="day" name="day" required>
                        <option value="">Select a Day</option>
                        <option value="SATURDAY" <c:if test="${meal.day == 'SATURDAY'}"> selected="selected" </c:if> >
                            SATURDAY
                        </option>
                        <option value="SUNDAY" <c:if test="${meal.day == 'SUNDAY'}"> selected="selected" </c:if> >
                            SUNDAY
                        </option>
                        <option value="MONDAY" <c:if test="${meal.day == 'MONDAY'}"> selected="selected" </c:if> >
                            MONDAY
                        </option>
                        <option value="TUESDAY" <c:if test="${meal.day == 'TUESDAY'}"> selected="selected" </c:if> >
                            TUESDAY
                        </option>
                        <option value="WEDNESDAY" <c:if test="${meal.day == 'WEDNESDAY'}"> selected="selected" </c:if> >
                            WEDNESDAY
                        </option>
                        <option value="THURSDAY" <c:if test="${meal.day == 'THURSDAY'}"> selected="selected" </c:if> >
                            THURSDAY
                        </option>
                        <option value="FRIDAY" <c:if test="${meal.day == 'FRIDAY'}"> selected="selected" </c:if> >
                            FRIDAY
                        </option>
                    </select>
                </div>

                <% List<Dish> dishList = (List<Dish>) request.getAttribute("dishLish"); %>
                <div class="form-group">
                    <label for="dish_list" class="sr-only">Dishes</label>
                    <select class="form-control" id="dish_list" name="dish_list" multiple required>
                        <c:forEach var="dish" items="${dishLish}">
                            <option value="${dish.id}" <c:if
                                    test="${fn:contains(meal.dishSet,dish)}"> selected="selected" </c:if> >${dish.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <input type="hidden" id="meal_id" name="meal_id" value="${meal.id}" required>

                <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>

            </form>

        </div>
    </div>
</div>
<!-- /container -->
<%@ include file="../footer.jsp" %>
</body>
</html>
