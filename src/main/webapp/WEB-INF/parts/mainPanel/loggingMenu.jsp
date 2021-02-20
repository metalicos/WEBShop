<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- Логувальне меню -->
<div class="nav-item dropdown d-flex">
    <a href="#" data-toggle="dropdown"
       class="nav-link dropdown-toggle mr-4 text-decoration-none text-dark">User
        <img src="../../design/img/account.png" alt="" width="30" height="30"
             class="align-self-center rounded-circle user-cabinet">
    </a>
    <div class="dropdown-menu action-form">
        <c:if test="${account == null}">
            <a href="controller?command=open-login-page"
               class="btn btn-primary col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                <span class="col-2 align-self-center fa fa-sign-in-alt"></span>
                <span class="col-10">Sign in</span>
            </a>
            <a href="controller?command=open-register-page"
               class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                <span class="col-2 align-self-center fa fa-address-card"></span>
                <span class="col-10">Registration</span>
            </a>
        </c:if>
        <a href="controller?command=open-cart-page"
           class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
            <span class="col-2 align-self-center fa fa-shopping-cart"></span>
            <span class="col-10">Shopping cart</span>
        </a>
        <c:if test="${account != null}">
            <a href="controller?command=open-profile-page"
               class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                <span class="col-2 align-self-center fa fa-user"></span>
                <span class="col-10">Your profile</span>
            </a>
            <a href="controller?command=open-orders-page"
               class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                <span class="col-2 align-self-center fa fa-list"></span>
                <span class="col-10">Your orders</span>
            </a>
            <c:if test="${accountRole.id == 1}">
                <a href="controller?command=open-orders-page"
                   class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                    <span class="col-2 align-self-center fa fa-list"></span>
                    <span class="col-10">Users</span>
                </a>
                <a href="controller?command=open-orders-page"
                   class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                    <span class="col-2 align-self-center fa fa-list"></span>
                    <span class="col-10">User orders</span>
                </a>
                <a href="controller?command=open-orders-page"
                   class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                    <span class="col-2 align-self-center fa fa-list"></span>
                    <span class="col-10">Create categories</span>
                </a>
                <a href="controller?command=open-orders-page"
                   class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                    <span class="col-2 align-self-center fa fa-list"></span>
                    <span class="col-10">Create products</span>
                </a>
            </c:if>
            <a href="controller?command=logout"
               class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                <span class="col-2 align-self-center fa fa-sign-out-alt"></span>
                <span class="col-10">Sign out</span>
            </a>
        </c:if>

    </div>
</div>
