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
<%@ include file="header.jsp" %>

<body>
<%@ include file="topbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <% User user = (User) request.getAttribute("editUser"); %>

            <form class="form-signin" method="post" action="<%= request.getContextPath() %>/edit-user">
                <h2 class="form-signin-heading">Update User Information</h2>

                <div class="form-group">
                <label for="username" class="sr-only">Username</label>
                <input type="text" id="username" class="form-control" placeholder="Username" name="username"
                       value="<%= user.getUsername() %>" disabled="disabled">
                </div>

                <div class="form-group">
                <label for="first_name" class="sr-only">First Name</label>
                <input type="text" id="first_name" class="form-control" placeholder="First Name" name="first_name"
                       value="<%= user.getFirstName() %>">
                </div>

                <div class="form-group">
                <label for="last_name" class="sr-only">Last Name</label>
                <input type="text" id="last_name" class="form-control" placeholder="Last Name" name="last_name"
                       value="<%= user.getLastName() %>">
                </div>

                <% User requestUser = ((User) request.getSession(false).getAttribute("user")); %>
                <% if (requestUser != null && requestUser.getIsSuperuser()) { %>
                <div class="form-group">
                    <label>Make Admin</label>
                    <input type="checkbox" value="admin" name="admin" <% if (user.getIsSuperuser()) {%> checked="checked" <% } %>>
                </div>
                <% } %>

                <div class="form-group">
                <label for="email" class="sr-only">Email</label>
                <input type="email" id="email" class="form-control" placeholder="Email" name="email" required
                       value="<%= user.getEmail() %>">
                </div>

                <input type="hidden" id="user_id" name="user_id" value="<%= user.getId() %>" required>
                <div class="form-group">
                <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
                </div>

            </form>
        </div>
    </div>
</div> <!-- /container -->
<%@ include file="footer.jsp" %>
</body>
</html>
