<%@ page import="com.mysql.cj.Session" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Registration"/>
        <jsp:param name="description"
                   value="Welcome to EShop. Create an account to be part of EShop. We provide special offers and qualificated support for our customers."/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/loginAndRegistration.css">
<%--    <style>--%>
<%--        <%@ include file="/design/css/loginAndRegistration.css"%>--%>
<%--    </style>--%>
</head>

<body class="m-1 m-md-5">

<div class="container-fluid px-sm-2 px-md-4 custom-wrapper d-block text-center">
    <div>
        <main class="form-signin py-5">
            <form id="registration_form" action="controller" method="get">
                <input type="hidden" name="command" value="register"/>

                <a href="controller?command=open-home-page">
                    <img class="mb-4" src="../design/img/logo.png" alt="" width="50" height="50">
                </a>

                <h1 class="h3 my-3 fw-normal">Реєстрація у EShop!</h1>

                <c:if test="${errorMessage!=null}">
                    <h1 class="h5 my-2 fw-normal text-center" style="color: #9b1512;!important;">${errorMessage}</h1>
                </c:if>

                <h1 class="h5 my-1 fw-normal text-start">П.І.Б. українською мовою</h1>

                <div class="d-flex my-1">
                    <label for="surname-ua" class="visually-hidden">Прізвище</label>
                    <input type="text" id="surname-ua" name="surname-ua" class="form-control border-dark"
                           placeholder="Прізвище" required
                           autofocus>

                    <label for="first-name-ua" class="visually-hidden">Ім'я</label>
                    <input type="text" id="first-name-ua" name="first-name-ua" class="form-control border-dark"
                           placeholder="Ім'я" required
                           autofocus>

                    <label for="patronymic-ua" class="visually-hidden">По батькові</label>
                    <input type="text" id="patronymic-ua" name="patronymic-ua" class="form-control border-dark"
                           placeholder="По батькові"
                           required
                           autofocus>
                </div>

                <h1 class="h5 my-1 fw-normal text-start">П.І.Б. англійською мовою</h1>

                <div class="d-flex my-1">
                    <label for="surname-en" class="visually-hidden">Surname</label>
                    <input type="text" id="surname-en" name="surname-en" class="form-control border-dark"
                           placeholder="Surname" required
                           autofocus>

                    <label for="first-name-en" class="visually-hidden">First name</label>
                    <input type="text" id="first-name-en" name="first-name-en" class="form-control border-dark"
                           placeholder="First name"
                           required
                           autofocus>

                    <label for="patronymic-en" class="visually-hidden">Patronymic</label>
                    <input type="text" id="patronymic-en" name="patronymic-en" class="form-control border-dark"
                           placeholder="Patronymic"
                           required
                           autofocus>
                </div>

                <h1 class="h5 my-1 fw-normal text-start">Електронна адреса</h1>
                <label for="email" class="visually-hidden">Email address</label>
                <input type="email" id="email" name="email" class="form-control border-dark my-1" placeholder="E-mail:"
                       required
                       autofocus>

                <h1 class="h5 my-1 fw-normal text-start">Створіть пароль</h1>

                <label for="password" class="visually-hidden">Password</label>
                <input type="password" id="password" name="password" class="form-control border-dark my-1"
                       placeholder="Password:"
                       required autofocus>

                <h1 class="h5 my-1 fw-normal text-start">Введіть пароль повторно</h1>

                <label for="check-password" class="visually-hidden">Password</label>
                <input type="password" id="check-password" name="check-password" class="form-control border-dark my-1"
                       placeholder="Password:"
                       required autofocus>

                <div class="form-text">
                    <p class="h6">Створюючи акаунт ви погоджуєтесе з<a class="mx-2 link-dark" data-toggle="modal"
                                                                       data-target="#collapseConditions">Умовами
                        використання </a>та<a class="mx-2 link-dark" data-toggle="modal" data-target="#privacyNotice">Політикою
                        конфіденційності</a></p>
                </div>

                <div class="modal fade" id="collapseConditions" tabindex="-1" role="dialog"
                     aria-labelledby="modal1" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modal1">Умови використання</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <%@ include file="/WEB-INF/parts/termsOfUsage.jsp" %>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрити
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="privacyNotice" tabindex="-1" role="dialog"
                     aria-labelledby="modal2" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modal2">Політика
                                    конфіденційності</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <%@ include file="/WEB-INF/parts/confidencePolicy.jsp" %>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрити
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Зареєструватися</button>

                <h6 class="mt-2">Уже є акаунт?
                    <a href="controller?command=open-login-page" class="a link-dark">Увійти</a>
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