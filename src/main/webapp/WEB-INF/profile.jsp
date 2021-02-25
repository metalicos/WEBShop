<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Profile"/>
        <jsp:param name="description"
                   value="Welcome to EShop. Change the information in profile to set the delivery adress."/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/profile.css">
</head>


<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>

<!-- Вміст -->
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="text-uppercase text-center ">
                <span class="text-warning h2">Персональний</span>
                <span class="ms-2 h2">Кабінет</span>
            </div>
            <h1 class="h4 text-danger text-center fw-bold">${errorMessage}</h1>
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="../design/img/accont.jpg" alt="Admin" class="rounded-circle" width="150">
                            <a href="#" class="align-self-end text-decoration-none text-dark">
                                <span class="fa fa-2x fa-edit"></span>
                            </a>
                            <div class="mt-3">
                                <h4 class="text-dark  h3">
                                    <c:if test="${language == 'ua'}">
                                        ${detailedAccount.firstNameUa} ${detailedAccount.surnameUa}
                                    </c:if>
                                    <c:if test="${language == 'en'}">
                                        ${detailedAccount.firstNameEn} ${detailedAccount.surnameEn}
                                    </c:if>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mt-3">
                    <div class="list-group-item d-flex justify-content-center align-items-center flex-wrap">
                        <button class="mb-0 btn btn-outline-warning border-2 text-dark w-100">
                            <span class="fa fa-list"></span>
                            Мої замовлення
                        </button>
                        <%--                        <button class="mb-0 btn btn-outline-warning border-2 text-dark col-5">--%>
                        <%--                            <span class="fa fa-heart"></span>--%>
                        <%--                            Лист бажань--%>
                        <%--                        </button>--%>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Прізвище</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.surnameUa}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Surname</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.surnameEn}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Ім'я</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.firstNameUa}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">First Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.firstNameEn}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">По батькові</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.patronymicUa}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Patronymic</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.patronymicEn}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">E-mail</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Моб. тел.</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.phone}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Країна</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.countryUa}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Country</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.countryEn}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Місто</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.cityUa}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">City</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.cityEn}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Вулиця</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.streetUa}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Street</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.streetEn}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Будинок</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.buildingUa}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Building</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.buildingEn}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Квартира</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.flatUa}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Flat</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${detailedAccount.flatEn}
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Поштовий індекс</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <c:if test="${detailedAccount.zipCode != '0'}">
                                    ${detailedAccount.zipCode}
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="d-flex align-content-center justify-content-between">
                    <button type="button" class="mb-0 btn btn-outline-warning border-2 text-dark w-auto"
                            data-toggle="modal" data-target="#form-personal-data">
                        Редагувати персональні дані
                    </button>
                    <button type="button" class="mb-0 btn btn-outline-warning border-2 text-dark"
                            data-toggle="modal" data-target="#form-change-password">
                        Змінити пароль
                    </button>
                </div>

                <div class="modal fade" id="form-personal-data" tabindex="-1" role="dialog"
                     aria-labelledby="label1"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-bottom-0">
                                <h5 class="modal-title" id="label1">Редагування персональних даних</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="change-account-details">
                                <div class="modal-body">
                                    <div class="card border-4 mb-3">
                                        <div class="card-body">
                                            <div class="row col-12">
                                                <h1 class="h5 col-12">П.І.Б. українською мовою</h1>
                                                <div class="col-sm-3">
                                                    <label for="surname-ua" class="visually-hidden">Прізвище</label>
                                                    <input type="text" id="surname-ua" name="surname-ua"
                                                           aria-describedby="surname-ua-help" placeholder="Прізвище"
                                                           value="${detailedAccount.surnameUa}">
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="first-name-ua" class="visually-hidden">Ім'я</label>
                                                    <input type="text" id="first-name-ua" name="first-name-ua"
                                                           aria-describedby="first-name-ua-help" placeholder="Ім'я"
                                                           value="${detailedAccount.firstNameUa}">
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="patronymic-ua" class="visually-hidden">По
                                                        батькові</label>
                                                    <input type="text" id="patronymic-ua" name="patronymic-ua"
                                                           aria-describedby="patronymic-ua-help"
                                                           placeholder="По батькові"
                                                           value="${detailedAccount.patronymicUa}">
                                                </div>

                                                <h1 class="mt-3 h5 col-12">П.І.Б. англійською мовою</h1>
                                                <div class="col-sm-3">
                                                    <label for="surname-en" class="visually-hidden">Surname</label>
                                                    <input type="text" id="surname-en" name="surname-en"
                                                           aria-describedby="surname-en-help" placeholder="Surname"
                                                           value="${detailedAccount.surnameEn}">
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="first-name-en" class="visually-hidden">First
                                                        name</label>
                                                    <input type="text" id="first-name-en" name="first-name-en"
                                                           aria-describedby="first-name-en-help"
                                                           placeholder="First name"
                                                           value="${detailedAccount.firstNameEn}">
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="patronymic-en"
                                                           class="visually-hidden">Patronymic</label>
                                                    <input type="text" id="patronymic-en" name="patronymic-en"
                                                           aria-describedby="patronymic-en-help"
                                                           placeholder="Patronymic"
                                                           value="${detailedAccount.patronymicEn}">
                                                </div>

                                                <h1 class="mt-3 h5 col-12">Електронна адреса</h1>
                                                <div class="col-12">
                                                    <label for="email" class="visually-hidden">Surname</label>
                                                    <input type="email" id="email" name="email"
                                                           aria-describedby="email-help" placeholder="E-mail"
                                                           value="${detailedAccount.email}">
                                                </div>
                                                <h1 class="mt-3 h5 col-12">Мобільний телефон</h1>
                                                <div class="col-12">
                                                    <label for="mobile" class="visually-hidden">Surname</label>
                                                    <input type="tel" id="mobile" name="mobile"
                                                           aria-describedby="mobile-help" placeholder="Mobile tel."
                                                           value="${detailedAccount.phone}">
                                                </div>

                                                <h1 class="mt-3 h5 col-12">Країна, місто, вулиця, буд., кв., українською
                                                    мовою</h1>
                                                <div class="d-flex align-content-between">
                                                    <div class="col-sm-6">
                                                        <label for="country-ua" class="visually-hidden">Країна</label>
                                                        <input type="text" id="country-ua" name="country-ua"
                                                               aria-describedby="country-ua-help" placeholder="Країна"
                                                               value="${detailedAccount.countryUa}">
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label for="city-ua" class="visually-hidden">Місто</label>
                                                        <input type="text" id="city-ua" name="city-ua"
                                                               aria-describedby="city-ua-help"
                                                               placeholder="Місто"
                                                               value="${detailedAccount.cityUa}">
                                                    </div>
                                                </div>
                                                <div class="mt-3 col-12">
                                                    <label for="street-ua" class="visually-hidden">Вулиця</label>
                                                    <input type="text" id="street-ua" name="street-ua"
                                                           aria-describedby="street-ua-help" placeholder="Вулиця"
                                                           value="${detailedAccount.streetUa}">
                                                </div>
                                                <div class="mt-3 d-flex align-content-between">
                                                    <div class="col-sm-6">
                                                        <label for="building-ua" class="visually-hidden">Будинок</label>
                                                        <input type="text" id="building-ua" name="building-ua"
                                                               aria-describedby="building-ua-help"
                                                               placeholder="Будинок"
                                                               value="${detailedAccount.buildingUa}">
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label for="flat-ua" class="visually-hidden">Квартира</label>
                                                        <input type="text" id="flat-ua" name="flat-ua"
                                                               aria-describedby="flat-ua-help"
                                                               placeholder="Квартира"
                                                               value="${detailedAccount.flatUa}">
                                                    </div>
                                                </div>

                                                <h1 class="mt-3 h5 col-12">Країна, місто, буд., кв., вулиця англійською
                                                    мовою</h1>
                                                <div class="d-flex align-content-between">
                                                    <div class="col-sm-6">
                                                        <label for="country-en" class="visually-hidden">Country</label>
                                                        <input type="text" id="country-en" name="country-en"
                                                               aria-describedby="country-en-help" placeholder="Country"
                                                               value="${detailedAccount.countryEn}">
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label for="city-en" class="visually-hidden">City</label>
                                                        <input type="text" id="city-en" name="city-en"
                                                               aria-describedby="city-en-help"
                                                               placeholder="City"
                                                               value="${detailedAccount.cityEn}">
                                                    </div>
                                                </div>
                                                <div class="mt-3 col-12">
                                                    <label for="street-en" class="visually-hidden">Street</label>
                                                    <input type="text" id="street-en" name="street-en"
                                                           aria-describedby="street-en-help" placeholder="Street"
                                                           value="${detailedAccount.streetEn}">
                                                </div>
                                                <div class="mt-3 d-flex align-content-between">
                                                    <div class="col-sm-6">
                                                        <label for="building-en"
                                                               class="visually-hidden">Building</label>
                                                        <input type="text" id="building-en" name="building-en"
                                                               aria-describedby="building-en-help"
                                                               placeholder="Building"
                                                               value="${detailedAccount.buildingEn}">
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label for="flat-en" class="visually-hidden">Flat</label>
                                                        <input type="text" id="flat-en" name="flat-en"
                                                               aria-describedby="flat-en-help"
                                                               placeholder="Flat"
                                                               value="${detailedAccount.flatEn}">
                                                    </div>
                                                </div>

                                                <h1 class="mt-3 h5 col-12">Поштовий індекс</h1>
                                                <div class="col-12">
                                                    <label for="zip-code" class="visually-hidden">Поштовий
                                                        індекс</label>
                                                    <input type="text" id="zip-code" name="zip-code"
                                                           aria-describedby="zip-code-ua-help"
                                                           placeholder="Поштовий індекс"
                                                           value="${detailedAccount.zipCode}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer border-top-0 d-flex justify-content-center">
                                        <button type="submit" class="btn btn-outline-success border-2">Зберегти</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="form-change-password" tabindex="-1" role="dialog"
                     aria-labelledby="label2"
                     aria-hidden="true">
                    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-bottom-0">
                                <h5 class="modal-title" id="label2">Змінити пароль</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Закрити">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-footer mt-0 pt-0 border-top-0 d-flex justify-content-center">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="change-account-password">

                                    <h1 class="mt-3 h5 col-12">Старий пароль</h1>
                                    <div class="col-12">
                                        <label for="old-password" class="visually-hidden">Старий пароль</label>
                                        <input type="password" id="old-password" name="old-password"
                                               aria-describedby="old-password-help"
                                               placeholder="Старий пароль" required autofocus>
                                    </div>
                                    <h1 class="mt-3 h5 col-12">Новий пароль</h1>
                                    <div class="col-12">
                                        <label for="new-password" class="visually-hidden">Новий пароль</label>
                                        <input type="password" id="new-password" name="new-password"
                                               aria-describedby="new-password-help"
                                               placeholder="Новий пароль" required autofocus>
                                    </div>
                                    <h1 class="mt-3 h5 col-12">Перевірка паролю</h1>
                                    <div class="col-12">
                                        <label for="check-new-password" class="visually-hidden">Новий
                                            пароль</label>
                                        <input type="password" id="check-new-password" name="check-new-password"
                                               aria-describedby="check-new-password-help"
                                               placeholder="Новий пароль, ще раз" required autofocus>
                                    </div>
                                    <button type="submit" class="mt-4 btn btn-outline-success border-2 col-12">Зберегти
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Section: Block Content-->
    <!--///////////////////////////////////////////////////////////-->
</div>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-full.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>

</body>
</html>