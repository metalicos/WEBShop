<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Shopping Cart"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/shoppingCart.css">
<%--    <style>--%>
<%--        <%@ include file="/design/css/shoppingCart.css"%>--%>
<%--    </style>--%>
</head>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>

<div class="container">
    <h1>Shopping Cart</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Product ID</th>
            <th scope="col">Product</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col" colspan="2"></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="product" items="${userShoppingCart}">
            <tr>
                <th scope="row">${product.id}</th>
                <td>${product.name}</td>
                <td>${product.categoryName}</td>
                <td>${product.price}</td>
                <td><a href="controller?command=delete-from-cart&productId=${product.id}" class="btn btn-outline-dark">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>