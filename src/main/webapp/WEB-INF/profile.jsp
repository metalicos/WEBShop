<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Profile"/>
        <jsp:param name="description" value="Welcome to EShop. Change the information in profile to set the delivery adress."/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/profile.css">
<%--    <style>--%>
<%--        <%@ include file="/design/css/profile.css"%>--%>
<%--    </style>--%>
</head>


<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp"%>

<!-- Вміст -->
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="text-uppercase text-center ">
                <span class="text-warning h2">Персональний</span>
                <span class="ms-2 h2">Кабінет</span>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="../design/img/accont.jpg" alt="Admin" class="rounded-circle" width="150">
                            <a href="#" class="align-self-end text-decoration-none text-dark">
                                <span class="fa fa-2x fa-edit"></span>
                            </a>
                            <div class="mt-3">
                                <h4 class="text-dark  h3">Ostap Komplikevych</h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mt-3">
                    <div class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                        <button class="mb-0 btn btn-outline-warning border-2 text-dark col-6">
                            <span class="fa fa-list"></span>
                            Мої замовлення
                        </button>
                        <button class="mb-0 btn btn-outline-warning border-2 text-dark col-5">
                            <span class="fa fa-heart"></span>
                            Лист бажань
                        </button>
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
                                Комплікевич
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Surname</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                Komplikevych
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Ім'я</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                Остап
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">First Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                Ostap
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">По батькові</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                Ярославович
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Patronymic</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                Yaroslavovych
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">E-mail</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ostap.ja@gmail.com
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Моб. тел.</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                +(380) 68 781 49 17
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Країна</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                Україна
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Місто</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                Львів
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Вулиця</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                С. Петлюри
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Буд.</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                66
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Квартира</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                33
                            </div>
                            <div class="col-sm-3">
                                <h6 class="mb-0">Поштовий індекс</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                79021
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
                            <form>
                                <div class="modal-body">
                                    <div class="card border-4 mb-3">
                                        <div class="card-body">
                                            <div class="row col-12">
                                                <h1 class="h5 col-12">П.І.Б. українською мовою</h1>
                                                <div class="col-sm-3">
                                                    <label for="surname-ua" class="visually-hidden">Прізвище</label>
                                                    <input type="text" id="surname-ua"
                                                           aria-describedby="surname-ua-help" placeholder="Прізвище">
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="first-name-ua" class="visually-hidden">Ім'я</label>
                                                    <input type="text" id="first-name-ua"
                                                           aria-describedby="first-name-ua-help" placeholder="Ім'я">
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="patronymic-ua" class="visually-hidden">По
                                                        батькові</label>
                                                    <input type="text" id="patronymic-ua"
                                                           aria-describedby="patronymic-ua-help"
                                                           placeholder="По батькові">
                                                </div>

                                                <h1 class="mt-3 h5 col-12">П.І.Б. англійською мовою</h1>
                                                <div class="col-sm-3">
                                                    <label for="surname-en" class="visually-hidden">Surname</label>
                                                    <input type="text" id="surname-en"
                                                           aria-describedby="surname-en-help" placeholder="Surname">
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="first-name-en" class="visually-hidden">First
                                                        name</label>
                                                    <input type="text" id="first-name-en"
                                                           aria-describedby="first-name-en-help"
                                                           placeholder="First name">
                                                </div>
                                                <div class="col-sm-3">
                                                    <label for="patronymic-en"
                                                           class="visually-hidden">Patronymic</label>
                                                    <input type="text" id="patronymic-en"
                                                           aria-describedby="patronymic-en-help"
                                                           placeholder="Patronymic">
                                                </div>

                                                <h1 class="mt-3 h5 col-12">Електронна адреса</h1>
                                                <div class="col-12">
                                                    <label for="email" class="visually-hidden">Surname</label>
                                                    <input type="email" id="email" aria-describedby="email-help"
                                                           placeholder="E-mail">
                                                </div>
                                                <h1 class="mt-3 h5 col-12">Мобільний телефон</h1>
                                                <div class="col-12">
                                                    <label for="mobile" class="visually-hidden">Surname</label>
                                                    <input type="mobile" id="mobile" aria-describedby="mobile-help"
                                                           placeholder="Mobile tel.">
                                                </div>

                                                <h1 class="mt-3 h5 col-12">Країна, місто, вулиця, буд., кв., українською
                                                    мовою</h1>
                                                <div class="d-flex align-content-between">
                                                    <div class="col-sm-6">
                                                        <label for="country-ua" class="visually-hidden">Країна</label>
                                                        <input type="text" id="country-ua"
                                                               aria-describedby="country-ua-help" placeholder="Країна">
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label for="city-ua" class="visually-hidden">Місто</label>
                                                        <input type="text" id="city-ua" aria-describedby="city-ua-help"
                                                               placeholder="Місто">
                                                    </div>
                                                </div>
                                                <div class="mt-3 col-12">
                                                    <label for="street-ua" class="visually-hidden">Вулиця</label>
                                                    <input type="text" id="street-ua"
                                                           aria-describedby="street-ua-help" placeholder="Вулиця">
                                                </div>
                                                <div class="mt-3 d-flex align-content-between">
                                                    <div class="col-sm-6">
                                                        <label for="building-ua" class="visually-hidden">Будинок</label>
                                                        <input type="text" id="building-ua"
                                                               aria-describedby="building-ua-help"
                                                               placeholder="Будинок">
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label for="flat-ua" class="visually-hidden">Квартира</label>
                                                        <input type="text" id="flat-ua" aria-describedby="flat-ua-help"
                                                               placeholder="Квартира">
                                                    </div>
                                                </div>

                                                <h1 class="mt-3 h5 col-12">Країна, місто, буд., кв., вулиця англійською
                                                    мовою</h1>
                                                <div class="d-flex align-content-between">
                                                    <div class="col-sm-6">
                                                        <label for="country-en" class="visually-hidden">Country</label>
                                                        <input type="text" id="country-en"
                                                               aria-describedby="country-en-help" placeholder="Country">
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label for="city-en" class="visually-hidden">City</label>
                                                        <input type="text" id="city-en" aria-describedby="city-en-help"
                                                               placeholder="City">
                                                    </div>
                                                </div>
                                                <div class="mt-3 col-12">
                                                    <label for="street-en" class="visually-hidden">Street</label>
                                                    <input type="text" id="street-en"
                                                           aria-describedby="street-en-help" placeholder="Street">
                                                </div>
                                                <div class="mt-3 d-flex align-content-between">
                                                    <div class="col-sm-6">
                                                        <label for="building-en"
                                                               class="visually-hidden">Building</label>
                                                        <input type="text" id="building-en"
                                                               aria-describedby="building-en-help"
                                                               placeholder="Building">
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label for="flat-en" class="visually-hidden">Flat</label>
                                                        <input type="text" id="flat-en" aria-describedby="flat-en-help"
                                                               placeholder="Flat">
                                                    </div>
                                                </div>

                                                <h1 class="mt-3 h5 col-12">Поштовий індекс</h1>
                                                <div class="col-12">
                                                    <label for="zip-code-ua" class="visually-hidden">Поштовий
                                                        індекс</label>
                                                    <input type="text" id="zip-code-ua"
                                                           aria-describedby="zip-code-ua-help"
                                                           placeholder="Поштовий індекс">
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
                                <form class="m-4">
                                    <h1 class="mt-3 h5 col-12">Старий пароль</h1>
                                    <div class="col-12">
                                        <label for="old-password" class="visually-hidden">Старий пароль</label>
                                        <input type="password" id="old-password"
                                               aria-describedby="old-password-help"
                                               placeholder="Старий пароль" required autofocus>
                                    </div>
                                    <h1 class="mt-3 h5 col-12">Новий пароль</h1>
                                    <div class="col-12">
                                        <label for="new-password" class="visually-hidden">Новий пароль</label>
                                        <input type="password" id="new-password"
                                               aria-describedby="new-password-help"
                                               placeholder="Новий пароль" required autofocus>
                                    </div>
                                    <h1 class="mt-3 h5 col-12">Перевірка паролю</h1>
                                    <div class="col-12">
                                        <label for="check-new-password" class="visually-hidden">Новий
                                            пароль</label>
                                        <input type="password" id="check-new-password"
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
<%@ include file="/WEB-INF/parts/footer-full.jsp"%>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp"%>

</body>
</html>