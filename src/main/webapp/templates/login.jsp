<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 10/25/16
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Meal Planner- Therap</title>

    <!-- Bootstrap core CSS -->
    <link href="/idea-jsp-servlet-tomcat-example/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/idea-jsp-servlet-tomcat-example/statics/css/signin.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <form class="form-signin" method="post" action="/idea-jsp-servlet-tomcat-example/login">
    <%--<form class="form-signin" method="post" action="/login">--%>
        <h2 class="form-signin-heading">Please sign in</h2>

        <label for="username" class="sr-only">Username</label>
        <input type="text" id="username" class="form-control" placeholder="Username" name="username" required autofocus>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control"  name="password" placeholder="Password" required>

        <input class="btn btn-lg btn-primary btn-block" value="Sign in" type="submit"/>
    </form>

</div> <!-- /container -->
</body>
</html>
