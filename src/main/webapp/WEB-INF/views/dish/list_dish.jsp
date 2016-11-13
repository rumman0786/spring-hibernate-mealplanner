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
            <c:if test="${user.isSuperuser}">
                <a href="${pageContext.request.contextPath}/admin/add-dish" class="btn btn-success pull-right">Add
                    Dish</a>
            </c:if>

            <h2 class="sub-header">Dish List</h2>
            <c:choose>

                <c:when test="${param['failure'] == 'failure'}">
                    <div class="alert alert-danger" role="alert">
                        Operation Failed
                    </div>
                </c:when>

                <c:when test="${param['success']== 'success'}">
                    <div class="alert alert-success" role="alert">
                        Operation Successful
                    </div>
                </c:when>

            </c:choose>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Calories</th>

                        <c:if test="${user.isSuperuser}">
                            <th>Edit</th>
                            <th>Delete</th>
                        </c:if>

                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="dish" items="${dishList}">
                        <tr>
                            <td>${dish.name}</td>
                            <td>${dish.calories}</td>

                            <c:if test="${user.isSuperuser}">
                                <td><a href="${pageContext.request.contextPath}/admin/edit-dish/?id=${dish.id}"><span
                                        class="glyphicon glyphicon-edit"></span></a></td>
                                <td><a href="#"
                                       data-href="${pageContext.request.contextPath}/admin/delete-dish/?id=${dish.id}"
                                       data-toggle="modal" data-target="#confirm-delete"
                                       class="delete-user-item"><span
                                        class="glyphicon glyphicon-trash"></span></a></td>
                            </c:if>

                        </tr>
                    </c:forEach>

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
                <p>You are about to delete a dish, this procedure is irreversible.</p>

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

