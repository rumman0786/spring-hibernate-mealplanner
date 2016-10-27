<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 10/27/16
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Meal Planner</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <%--<li><a href="#">Dashboard</a></li>--%>
                <%--<li><a href="#">Settings</a></li>--%>
                <%--<li><a href="#">Profile</a></li>--%>
                <li><a href="/idea-jsp-servlet-tomcat-example/logout">Logout</a></li>
            </ul>
            <%--<form class="navbar-form navbar-right">--%>
                <%--<input type="text" class="form-control" placeholder="Search...">--%>
            <%--</form>--%>
        </div>
    </div>
</nav>