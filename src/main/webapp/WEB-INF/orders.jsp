<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Orders"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/orders.css">
</head>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp"%>





<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>