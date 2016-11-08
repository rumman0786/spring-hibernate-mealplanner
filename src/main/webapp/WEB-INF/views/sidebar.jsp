<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 10/27/16
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">

        <c:set var="active" scope="session" value="active"/>

        <li class="<c:if test="${page == 'user'}">
                        <c:out value="${active}"/>
                    </c:if>"><a
                href="<%= request.getContextPath() %>/user-list">User Management</a></li>


        <li class="<c:if test="${page == 'dish'}">
                        <c:out value="${active}"/>
                    </c:if>"><a
                href="<%= request.getContextPath() %>/dish-list">Dish Management</a></li>

        <li class="<c:if test="${page == 'meal'}">
                        <c:out value="${active}"/>
                    </c:if>"><a
                href="<%= request.getContextPath() %>/meal-list">Meal Management</a></li>
    </ul>

</div>