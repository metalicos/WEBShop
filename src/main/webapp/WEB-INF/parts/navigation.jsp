<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- Меню для ПК -->
<div class="px-5 d-none d-lg-block container-fluid bg-light py-2" style="background-color: #86bd57!important;">
    <nav class="container-md d-flex justify-content-md-between justify-content-sm-center">
        <!-- Логотип -->
        <%@ include file="mainPanel/logotype.jsp" %>
        <!-- Меню -->
        <div class="d-none d-md-flex col-lg-10 col-xl-9 justify-content-between align-content-center">
            <!-- Пошукова стрічка -->
            <%@ include file="mainPanel/search-bar.jsp" %>
            <!-- Контакти -->
            <%@ include file="mainPanel/contacts.jsp" %>
            <!-- Вибір мови -->
            <%@ include file="mainPanel/languageChooser.jsp" %>
            <!-- Корзина -->
            <%@ include file="mainPanel/shoppingCart.jsp" %>
            <!-- Логувальне меню -->
            <%@ include file="mainPanel/loggingMenu.jsp" %>
        </div>
    </nav>
</div>

<!-- Меню для телефонів -->
<div class="px-5 d-block d-lg-none container-fluid bg-light py-2" style="background-color: #86bd57!important;">
    <div class="d-flex justify-content-between ">
        <!-- Логотип -->
        <%@ include file="mainPanel/logotype.jsp" %>

        <div class="col-sm-2 col-md-2 d-flex justify-content-between">
            <!-- Корзина -->
            <%@ include file="mainPanel/shoppingCart.jsp" %>
            <!-- Логувальне меню -->
            <%@ include file="mainPanel/loggingMenu.jsp" %>
        </div>
    </div>
</div>

