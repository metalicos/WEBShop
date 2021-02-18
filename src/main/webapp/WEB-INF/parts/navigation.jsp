<%@ page import="com.ostap.komplikevych.webshop.entity.Account" %>
<%@ page import="com.ostap.komplikevych.webshop.constant.SessionAttribute" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!-- Меню для ПК -->
<div class="px-5 d-none d-lg-block container-fluid bg-light py-2" style="background-color: #86bd57!important;">
    <nav class="container-md d-flex justify-content-md-between justify-content-sm-center">
        <!-- Логотип -->
        <a class="col-lg-2 col-xl-3 d-flex justify-content-lg-start text-decoration-none" href="controller?command=open-home-page">
            <img src="../../design/img/logo.png" alt="" width="40" height="40" class="align-self-center logotype-icon">
            <h1 class="align-self-center pt-1 px-2 logotype"
                style="color: #000000;
                font-size: 25px;
                font-weight: bold">
                EShop
            </h1>
        </a>
        <!-- Меню -->
        <div class="d-none d-md-flex col-lg-10 col-xl-9 justify-content-between align-content-center">
            <!-- Пошукова стрічка -->
            <form class="col-lg-3 col-xl-4 align-self-center">
                <div class="input-group">
                    <button type="button" class="input-group-text btn-group"><span
                            class="fas fa-search"></span>
                    </button>
                    <input type="text" class="form-control" placeholder="Я шукаю..." aria-label="Шукати"
                           aria-describedby="basic-addon1">
                </div>
            </form>
            <!-- Номер телефону -->
            <h6 class="align-self-center"><span class="fas fa-phone"></span>
                <a class="text-black-50 text-decoration-none" href="#"> (068)781-49-17</a>
            </h6>
            <!-- Вибір мови -->
            <h6 class="align-self-center">
                <a class="text-black-50" href="controller?command=set-language&language=UA">UA</a>
                |
                <a class="text-black-50" href="controller?command=set-language&language=EN">EN</a>
            </h6>
            <!-- Вибір міста -->
            <h6 class="align-self-center">Місто
                <a class="text-black-50 text-decoration-underline" href="#">Львів</a>
            </h6>
            <!-- Корзина -->
            <a href="#" class="d-flex align-items-center text-dark">
                <h5>
                    <span class="fas fa-shopping-cart"></span>
                </h5>
                <span class="badge bg-danger">0</span>
            </a>

            <!-- Логувальне меню -->
            <div class="nav-item dropdown d-flex">
                <a href="#" data-toggle="dropdown"
                   class="nav-link dropdown-toggle mr-4 text-decoration-none text-dark">Ostap
                    <img src="../../design/img/person2.jpg" alt="" width="30" height="30"
                         class="align-self-center rounded-circle user-cabinet">
                </a>
                <div class="dropdown-menu action-form">

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
                    <a href="controller?command=open-cart-page"
                       class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                        <span class="col-2 align-self-center fa fa-shopping-cart"></span>
                        <span class="col-10">Shopping cart</span>
                    </a>
                    <a href="controller?command=open-logout-page"
                       class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                        <span class="col-2 align-self-center fa fa-sign-out-alt"></span>
                        <span class="col-10">Sign out</span>
                    </a>

                </div>
            </div>

        </div>
    </nav>
</div>

<!-- Меню для телефонів -->
<div class="px-5 d-block d-lg-none container-fluid bg-light py-2" style="background-color: #86bd57!important;">
    <div class="d-flex justify-content-between ">
        <!-- Логотип -->
        <a class="col-lg-2 col-xl-3 d-flex justify-content-lg-start text-decoration-none" href="controller?command=open-home-page">
            <img src="../../design/img/logo.png" alt="" width="40" height="40" class="align-self-center logotype-icon">
            <h1 class="align-self-center pt-1 px-2 logotype"
                style="color: #000000;
                font-size: 25px;
                font-weight: bold">
                EShop
            </h1>
        </a>

        <div class="col-sm-2 col-md-2 d-flex justify-content-between">
            <!-- Корзина -->
            <a href="#" class="d-flex align-items-center text-dark pe-3">
                <h5>
                    <span class="fas fa-shopping-cart"></span>
                </h5>
                <span class="badge bg-danger">0</span>
            </a>
            <!-- Логувальне меню -->
            <div class="nav-item dropdown d-flex">
                <a href="#" data-toggle="dropdown"
                   class="nav-link dropdown-toggle mr-4 text-decoration-none text-dark">
                    <img src="../../design/img/person2.jpg" alt="" width="30" height="30"
                         class="align-self-center rounded-circle user-cabinet">
                </a>
                <div class="dropdown-menu action-form">

                    <a href="login"
                       class="btn btn-primary col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                        <span class="col-2 align-self-center fa fa-sign-in-alt"></span>
                        <span class="col-10">Sign in</span>
                    </a>
                    <a href="registration"
                       class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                        <span class="col-2 align-self-center fa fa-address-card"></span>
                        <span class="col-10">Registration</span>
                    </a>
                    <a href="profile"
                       class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                        <span class="col-2 align-self-center fa fa-user"></span>
                        <span class="col-10">Your profile</span>
                    </a>
                    <a href="orders"
                       class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                        <span class="col-2 align-self-center fa fa-list"></span>
                        <span class="col-10">Your orders</span>
                    </a>
                    <a href="cart"
                       class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                        <span class="col-2 align-self-center fa fa-shopping-cart"></span>
                        <span class="col-10">Shopping cart</span>
                    </a>
                    <a href="login"
                       class="btn btn-outline-dark col-11 mx-2 my-2 d-flex align-content-center justify-content-between">
                        <span class="col-2 align-self-center fa fa-sign-out-alt"></span>
                        <span class="col-10">Sign out</span>
                    </a>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- Меню категорій товарів -->
<button class="px-5 py-2 navbar-toggler btn shadow-none d-block d-lg-none" type="button" data-toggle="collapse"
        data-target="#navbarToggleMenu"
        aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
    <h6 class="text-dark align-self-center" style="font-size: 13px;"><span class="fas fa-bars fa-2x align-self-center"> Каталог товарів</span>
    </h6>
</button>


<div class="px-5 container-fluid">
    <div class="collapse navbar-collapse" id="navbarToggleMenu">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="#">Контролери</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Сенсори</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Паяльники</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Плати</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Флюси</a>
            </li>
        </ul>

        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

    </div>
</div>
