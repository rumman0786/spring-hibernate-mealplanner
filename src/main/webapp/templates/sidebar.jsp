<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 10/27/16
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <%--<li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>--%>
        <li class="<% if (request.getAttribute("page") == "user") {out.print("active");} %>"><a
                href="<% out.print(request.getContextPath()); %>/user-list">User Management</a></li>

        <li class="<% if (request.getAttribute("page") == "dish") {out.print("active");} %>"><a
                href="<% out.print(request.getContextPath()); %>/dish-list">Dish Management</a></li>

        <li class="<% if (request.getAttribute("page") == "meal") {out.print("active");} %>"><a
                href="<% out.print(request.getContextPath()); %>/meal-list">Meal Management</a></li>
    </ul>
    <%--<ul class="nav nav-sidebar">--%>
    <%--<li><a href="">Nav item</a></li>--%>
    <%--<li><a href="">Nav item again</a></li>--%>
    <%--<li><a href="">One more nav</a></li>--%>
    <%--<li><a href="">Another nav item</a></li>--%>
    <%--<li><a href="">More navigation</a></li>--%>
    <%--</ul>--%>
    <%--<ul class="nav nav-sidebar">--%>
    <%--<li><a href="">Nav item again</a></li>--%>
    <%--<li><a href="">One more nav</a></li>--%>
    <%--<li><a href="">Another nav item</a></li>--%>
    <%--</ul>--%>
</div>