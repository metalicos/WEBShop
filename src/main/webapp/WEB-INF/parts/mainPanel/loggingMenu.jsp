<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- Логувальне меню -->
<div class="nav-item dropstart d-flex">
    <a href="#" data-toggle="dropdown"
       class="nav-link dropdown-toggle pr-4 text-decoration-none text-dark ">
        <c:if test="${language == 'ua'}">
            ${detailedAccount.firstNameUa}
        </c:if>
        <c:if test="${language == 'en'}">
            ${detailedAccount.firstNameEn}
        </c:if>
        <c:choose>
            <c:when test="${detailedAccount.accountPhoto!=null}">
                <img src="data:image/jpeg;base64,${detailedAccount.accountPhoto}"
                     class="rounded-circle" style="width: 30px; height: 30px;">
            </c:when>
            <c:otherwise>
                <img src="../../design/img/account.png" alt=""
                     class="align-self-center rounded-circle user-cabinet" style="width: 30px; height: 30px;">
            </c:otherwise>
        </c:choose>
    </a>
    <div class="dropdown-menu" style="margin-left:-150%; width: 200px;">
        <c:if test="${account == null}">
            <a href="controller?command=open-login-page"
               class="btn btn-primary col-11 mx-2 my-2 d-flex">
                <span class="col-2 align-self-center fa fa-sign-in-alt"></span>
                <span class="col-10">Sign in</span>
            </a>
            <a href="controller?command=open-register-page"
               class="btn btn-outline-dark col-11 mx-2 my-2 d-flex">
                <span class="col-2 align-self-center fa fa-address-card"></span>
                <span class="col-10">Registration</span>
            </a>
        </c:if>
        <a href="controller?command=open-cart-page"
           class="btn btn-outline-dark col-11 mx-2 my-2 d-flex">
            <span class="col-2 align-self-center fa fa-shopping-cart"></span>
            <span class="col-10">Shopping cart</span>
        </a>
        <c:if test="${account != null}">
            <a href="controller?command=open-profile-page"
               class="btn btn-outline-dark col-11 mx-2 my-2 d-flex">
                <span class="col-2 align-self-center fa fa-user"></span>
                <span class="col-10">Your profile</span>
            </a>
            <a href="controller?command=open-orders-page"
               class="btn btn-outline-dark col-11 mx-2 my-2 d-flex">
                <span class="col-2 align-self-center fa fa-list"></span>
                <span class="col-10">Your orders</span>
            </a>
            <c:if test="${accountRole.id == 1}">
                <a href="controller?command=open-users-page"
                   class="btn btn-outline-dark col-11 mx-2 my-2 d-flex">
                    <span class="col-2 align-self-center fa fa-list"></span>
                    <span class="col-10">Users</span>
                </a>
                <a href="controller?command=open-user-orders-page"
                   class="btn btn-outline-dark col-11 mx-2 my-2 d-flex">
                    <span class="col-2 align-self-center fa fa-list"></span>
                    <span class="col-10">User orders</span>
                </a>
                <a href="controller?command=open-create-category"
                   class="btn btn-outline-dark col-11 mx-2 my-2 d-flex">
                    <span class="col-2 align-self-center fa fa-plus-circle"></span>
                    <span class="col-10">Create category</span>
                </a>
                <a href="controller?command=open-create-product"
                   class="btn btn-outline-dark col-11 mx-2 my-2 d-flex">
                    <span class="col-2 align-self-center fa fa-plus-circle"></span>
                    <span class="col-10">Create product</span>
                </a>
            </c:if>
            <a href="controller?command=logout"
               class="btn btn-outline-dark col-11 mx-2 my-2 d-flex">
                <span class="col-2 align-self-center fa fa-sign-out-alt"></span>
                <span class="col-10">Sign out</span>
            </a>
        </c:if>
    </div>
</div>
