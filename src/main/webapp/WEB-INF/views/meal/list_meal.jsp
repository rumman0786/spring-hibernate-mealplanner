<%@ page import="net.therap.mealplanner.entity.Dish" %>
<%@ page import="net.therap.mealplanner.entity.Meal" %>
<%@ page import="net.therap.mealplanner.entity.User" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 10/24/16
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../header.jsp" %>

<body>
<%@ include file="../topbar.jsp" %>

<div class="container-fluid">
    <div class="row">

        <%@ include file="../sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <%-- Checks if admin user, shows add option to admin user only--%>
            <% if (((User) request.getSession(false).getAttribute("user")).getIsSuperuser()) { %>
            <a href="<%= request.getContextPath() %>/admin/add-meal" class="btn btn-success pull-right">Add Meal</a>
            <% } %>
            <h2 class="sub-header">Meal List</h2>

            <% if (request.getParameter("failure") != null && request.getParameter("failure").equals("failure")) { %>
            <div class="alert alert-danger" role="alert">
                Operation Failed
            </div>
            <% } else if (request.getParameter("success") != null && request.getParameter("success").equals("success")) { %>
            <div class="alert alert-success" role="alert">
                Operation Successful
            </div>
            <% } %>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Day</th>
                        <th>Items</th>
                        <th>Type</th>
                        <%-- Checks if admin user, shows add option to admin user only--%>
                        <% if (((User) request.getSession(false).getAttribute("user")).getIsSuperuser()) { %>
                        <th>Edit</th>
                        <th>Delete</th>
                        <% } %>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        ArrayList<Meal> mealList = (ArrayList<Meal>) request.getAttribute("mealList");
                        for (Meal meal : mealList) { %>
                    <tr>
                        <td><%= meal.getName() %>
                        </td>
                        <td><%= meal.getDay() %>
                        </td>
                        <td>
                            <% for (Dish dish : meal.getDishSet()) { %>
                            <%=     dish.getName() + " " %>
                            <% } %>
                        </td>
                        <td><%= meal.getMenuType().getCategory() %>
                        </td>
                        <%-- Checks if admin user, shows add option to admin user only--%>
                        <% if (((User) request.getSession(false).getAttribute("user")).getIsSuperuser()) { %>
                        <td>
                            <a href="<%= request.getContextPath() %>/admin/edit-meal/?id=<%= meal.getId() %>"><span
                                    class="glyphicon glyphicon-edit"></span><a/></td>
                        <td><a href="#"
                               data-href="<%= request.getContextPath() %>/admin/delete-meal/?id=<%= meal.getId() %>"
                               data-toggle="modal" data-target="#confirm-delete" class="delete-user-item"><span
                                class="glyphicon glyphicon-trash"></span></a></td>
                        <% } %>
                    </tr>

                    <% } %>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>

</body>
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
            </div>

            <div class="modal-body">
                <p>You are about to delete a meal, this procedure is irreversible.</p>

                <p>Do you want to proceed?</p>

                <p class="debug-url"></p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a href="#" class="btn btn-danger btn-ok">Delete</a>
            </div>
        </div>
    </div>
</div>

<script>
    $('.delete-user-item').on('click', function (e) {
        var criteria_id = $(this).attr("data-href");
        $('.btn-ok').attr("href", criteria_id);
    });
</script>
</html>

