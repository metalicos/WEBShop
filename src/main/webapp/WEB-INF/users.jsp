<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Users"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/users.css">
</head>

<style>


</style>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>

<div class="container my-5" id="main">
    <%--    <form action="controller" method="get" class=" row d-md-flex d-sm-block justify-content-between">--%>
    <%--        <input type="hidden" name="command" value="select-orders">--%>

    <%--        <h1 class="h5 align-self-center col-3">Search User</h1>--%>

    <%--        <div class="btn-group align-self-center col-12 col-sm-12 col-md-8 col-lg-6">--%>
    <%--            <select name="searchType" class="btn btn-outline-dark col-3 col-sm-3">--%>
    <%--                <option value="orderId">User ID</option>--%>
    <%--                <option value="name">Name</option>--%>
    <%--                <option value="status">Status</option>--%>
    <%--            </select>--%>
    <%--            <input type="search" name="searchBy" class="col-6 col-sm-6">--%>
    <%--            <input type="submit" value="Search" class="btn btn-outline-dark col-3 col-sm-3">--%>
    <%--        </div>--%>
    <%--    </form>--%>

    <table class="table">
        <thead>
        <tr>
            <td>ID</td>
            <td>Photo</td>
            <td>Name</td>
            <td>Status</td>
            <td>Role</td>
            <td>Change Status</td>
            <td>Change Role</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="acc" items="${accounts}">
            <tr>
                <td>${acc.id}</td>
                <td>
                    <c:choose>
                        <c:when test="${acc.accountPhoto!=null}">
                            <img src="data:image/jpeg;base64,${acc.accountPhoto}"
                                 class="rounded-circle" style="width: 100px;">
                        </c:when>
                        <c:otherwise>
                            <img src="../../design/img/account.png" alt=""
                                 class="align-self-center rounded-circle user-cabinet" style="width: 100px;">
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${acc.getFullNameUa()}</td>
                <td class="${status}">${acc.getAccountStatus()}</td>
                <td class="${role}">${acc.getRole()}</td>
                <td>
                    <form method="get" action="controller">
                        <input type="hidden" name="command" value="change-account-status">
                        <input type="hidden" name="accountId" value="${acc.id}">
                        <label for="radio1">ENABLE</label>
                        <input type="radio" id="radio1" name="statusId" value="1">
                        <label for="radio2">DISABLE</label>
                        <input type="radio" id="radio2" name="statusId" value="2">
                        <input type="submit" value="OK">
                    </form>
                </td>
                <td>
                    <form method="get" action="controller">
                        <input type="hidden" name="command" value="change-account-role">
                        <input type="hidden" name="accountId" value="${acc.id}">
                        <label for="radio3">ADMIN</label>
                        <input type="radio" id="radio3" name="roleId" value="1">
                        <label for="radio4">USER</label>
                        <input type="radio" id="radio4" name="roleId" value="2">
                        <input type="submit" value="OK">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-full.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>


</body>
</html>