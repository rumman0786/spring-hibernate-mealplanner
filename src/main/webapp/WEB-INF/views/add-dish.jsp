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
            <form class="form-signin" method="post" action="<%= request.getContextPath() %>/admin/add-dish">
                <h2 class="form-signin-heading">Add a Dish</h2>

                <div class="form-group">
                    <label for="dishname" class="sr-only">Dish Name</label>
                    <input type="text" id="dishname" class="form-control" placeholder="Dish Name" name="dishname"
                           required
                           autofocus>
                </div>

                <div class="form-group">
                    <label for="calories" class="sr-only">Calories</label>
                    <input type="text" id="calories" class="form-control" name="calories" placeholder="Calories"
                           required>
                </div>

                <div class="form-group">
                    <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- /container -->
<%@ include file="footer.jsp" %>
</body>
</html>
