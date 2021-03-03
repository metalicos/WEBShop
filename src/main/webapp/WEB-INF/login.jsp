<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="description" value="Welcome to EShop. Login to order a new item."/>
        <jsp:param name="pageName" value="EShop | Login"/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/loginAndRegistration.css">
</head>

<body class="m-3 m-md-5">

<%-- Вміст --%>
<div class="container-fluid px-sm-2 px-md-4 custom-wrapper d-block text-center">
    <div>
        <main class="form-signin p-3">
            <form id="login_form" action="controller" method="post">
                <input type="hidden" name="command" value="login"/>

                <a href="controller?command=open-home-page">
                    <img class="mb-4" src="../design/img/logo.png" alt="" width="50" height="50">
                </a>
                <h1 class="h3 mb-3 fw-normal">Вітаємо у EShop!</h1>

                <c:if test="${errorMessage!=null}">
                    <h1 class="h5 my-2 fw-normal text-center" style="color: #9b1512;!important;">${errorMessage}</h1>
                    <c:set var="errorMessage" scope="session" value="${''}"/>
                </c:if>

                <label for="inputEmail" class="visually-hidden">Email address</label>
                <input type="email" name="email" id="inputEmail" class="mb-3 form-control border-dark"
                       placeholder="E-mail:" required
                       autofocus>

                <label for="inputPassword" class="visually-hidden">Password</label>
                <input type="password" name="password" id="inputPassword" class="mb-3 form-control border-dark"
                       placeholder="Password:"
                       required autofocus>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Увійти</button>
                <h6 class="mt-2">Досі немає акаунту?
                    <a href="controller?command=open-register-page" class="a link-dark">Зареєструватися</a>
                </h6>
            </form>
        </main>
    </div>
</div>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>
