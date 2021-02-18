<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="ERROR"/>
        <jsp:param name="description" value="ERROR"/>
    </jsp:include>
    <link rel="stylesheet" href="design/css/index.css">
</head>
<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp"%>

<%-- Вміст --%>
<div class="page-wrap d-flex flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 text-center">
                <span class="display-1 d-block">ERROR</span>
                <div class="mb-4 lead">Something had happened!</div>
                <a href="/" class="btn btn-link">Back to Home</a>
            </div>
        </div>
    </div>
</div>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp"%>
</body>
</html>