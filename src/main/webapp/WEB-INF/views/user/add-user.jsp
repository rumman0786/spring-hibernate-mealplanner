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
<%@ include file="../header.jsp" %>

<body>
<%@ include file="../topbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="../sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <form:form class="form-signin" method="post" action="${pageContext.request.contextPath}/add-user" commandName="formUser">
                <h2 class="form-signin-heading">User Registraion</h2>

                <div class="form-group">
                    <label for="username" class="sr-only">Username</label>
                    <form:input type="text" id="username" class="form-control" placeholder="Username" name="username"
                          path="username"  autofocus="autofocus"/>
                    <form:errors path="username" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="first_name" class="sr-only">First Name</label>
                    <form:input type="text" id="first_name" class="form-control" placeholder="First Name" name="first_name" path="firstName" />
                    <form:errors path="firstName" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="last_name" class="sr-only">Last Name</label>
                    <form:input type="text" id="last_name" class="form-control" placeholder="Last Name" name="last_name" path="lastName" />
                    <form:errors path="lastName" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="password" class="sr-only">Password</label>
                    <form:input type="password" id="password" class="form-control" name="password" placeholder="Password" path="password"
                                 />
                    <form:errors path="password" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="confirm_password" class="sr-only">Confirm Password</label>
                    <form:input type="password" id="confirm_password" class="form-control" name="confirm_password" placeholder="Confirm Password" path="confirmPassword"
                                 />
                    <form:errors path="confirmPassword" cssClass="error" />
                </div>

                <c:if test="${user.isSuperuser}">
                    <div class="form-group">
                        <label>Make Admin</label>
                        <form:checkbox value="admin" name="admin" path="isSuperuser"/>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="email" class="sr-only">Email</label>
                    <form:input type="email" id="email" class="form-control" placeholder="Email" path="email" name="email"
                           autofocus="autofocus"/>
                    <form:errors path="email" cssClass="error" />
                </div>

                <div class="form-group">
                    <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
                </div>
            </form:form>

        </div>
    </div>
</div>
<!-- /container -->
<%@ include file="../footer.jsp" %>
</body>
</html>
