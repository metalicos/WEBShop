<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Users"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/users.css">
<%--    <style>--%>
<%--        <%@ include file="/design/css/users.css"%>--%>
<%--    </style>--%>
</head>


<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp"%>

<h1>Users</h1>

<table class="table table-bordered">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Surname</th>
        <th scope="col">First name</th>
        <th scope="col">Patronymic</th>
        <th scope="col">User Role</th>

        <th scope="col">User Status</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <th scope="row">1</th>
        <td>Komplikevych</td>
        <td>Ostap</td>
        <td>Yaroslavovych</td>
        <td>ADMIN</td>
        <td>ENABLED</td>
        <td><a href="" class="btn btn-outline-dark">Edit</a></td>
        <td><a href="" class="btn btn-outline-dark">Delete</a></td>
    </tr>
    </tbody>
</table>


<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-full.jsp"%>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp"%>

</body>
</html>