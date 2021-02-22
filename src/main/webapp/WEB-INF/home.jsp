<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop"/>
        <jsp:param name="description"
                   value="Welcome to EShop. The best Ukrainian Internet shop. Fast delivery, best offer."/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/home.css">
</head>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>


<!-- Наповнення сторінки -->
<div class="wrapper">

    <!-- Фльтри -->
    <div class="d-lg-flex align-items-lg-center pt-2 justify-content-between">

        <!-- Категорія -->
        <div class="d-md-flex align-items-md-center">
            <div class="h3">${categoryLabel}</div>
        </div>

        <div class="form-inline d-flex align-items-center my-2 mr-lg-2 radio bg-light border">

            <label class="options">Назва A-Z
                <input type="radio" id="r1" name="radio"
                       value="controller?command=sort-products&sort=name_a_z" ${checkedR1}
                       onclick="{window.location = $(this).val();}">
                <span class="checkmark"></span>
            </label>
            <label class="options">Назва Z-A
                <input type="radio" id="r2" name="radio"
                       value="controller?command=sort-products&sort=name_z_a" ${checkedR2}
                       onclick="{window.location = $(this).val();}">
                <span class="checkmark"></span>
            </label>
            <label class="options">Ціна
                <span class="fa fa-arrow-up"></span>
                <input type="radio" id="r3" name="radio"
                       value="controller?command=sort-products&sort=price_up" ${checkedR3}
                       onclick="{window.location = $(this).val();}">
                <span class="checkmark"></span>
            </label>
            <label class="options">Ціна
                <span class="fa fa-arrow-down"></span>
                <input type="radio" id="r4" name="radio"
                       value="controller?command=sort-products&sort=price_down" ${checkedR4}
                       onclick="{window.location = $(this).val();}">
                <span class="checkmark"></span>
            </label>
            <label class="options">Найновіші
                <input type="radio" id="r5" name="radio"
                       value="controller?command=sort-products&sort=date_new" ${checkedR5}
                       onclick="{window.location = $(this).val();}">
                <span class="checkmark"></span>
            </label>
        </div>
    </div>

    <div class="filters">
        <button class="btn btn-success" type="button" data-toggle="collapse" data-target="#mobile-filter"
                aria-expanded="true" aria-controls="mobile-filter">Filter<span class="px-1 fas fa-filter"></span>
        </button>
    </div>

    <div id="mobile-filter">
        <!-- Категорії -->
        <div class="py-3">
            <h5 class="font-weight-bold">Категорії</h5>
            <ul class="list-group">
                <a class="text-decoration-none"
                   href="/">
                    <li class="list-group-item list-group-item-action d-flex
                        justify-content-between align-items-center category">
                        Усі товари
                        <span class="badge badge-primary badge-pill">${productsFound}</span>
                    </li>
                </a>
                <c:forEach items="${categoryNamesWithProductAmountMap}" var="entry">
                    <a class="text-decoration-none"
                       href="controller?command=select-by-selector&selector=category&value=${entry.key}">
                        <li class="list-group-item list-group-item-action d-flex
                                                justify-content-between align-items-center category">
                                ${entry.key}
                            <span class="badge badge-primary badge-pill">${entry.value}</span>
                        </li>
                    </a>
                </c:forEach>
            </ul>
        </div>
        <!-- Кольори -->
        <div class="py-3">
            <h5 class="font-weight-bold">Кольори</h5>
            <ul class="list-group">
                <c:forEach items="${colorNamesWithProductAmountMap}" var="entry">
                    <a class="text-decoration-none"
                       href="controller?command=select-by-selector&selector=color&value=${entry.key}">
                        <li class="list-group-item list-group-item-action d-flex
                                                justify-content-between align-items-center category">
                                ${entry.key}
                            <span class="badge badge-primary badge-pill">${entry.value}</span>
                        </li>
                    </a>
                </c:forEach>
            </ul>
        </div>
        <!-- Розміри -->
        <div class="py-3">
            <h5 class="font-weight-bold">Розміри</h5>
            <ul class="list-group">
                <c:forEach items="${sizeNamesWithProductAmountMap}" var="entry">
                    <a class="text-decoration-none"
                       href="controller?command=select-by-selector&selector=size&value=${entry.key}">
                        <li class="list-group-item list-group-item-action d-flex
                                                justify-content-between align-items-center category">
                                ${entry.key}
                            <span class="badge badge-primary badge-pill">${entry.value}</span>
                        </li>
                    </a>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="content py-md-0 py-3">
        <section id="sidebar">
            <!-- Категорії -->
            <div class="py-3">
                <h5 class="font-weight-bold">Категорії</h5>
                <ul class="list-group">
                    <a class="text-decoration-none"
                       href="/">
                        <li class="list-group-item list-group-item-action d-flex
                        justify-content-between align-items-center category">
                            Усі товари
                            <span class="badge badge-primary badge-pill">${productsFound}</span>
                        </li>
                    </a>
                    <c:forEach items="${categoryNamesWithProductAmountMap}" var="entry">
                        <a class="text-decoration-none"
                           href="controller?command=select-by-selector&selector=category&value=${entry.key}">
                            <li class="list-group-item list-group-item-action d-flex
                                                justify-content-between align-items-center category">
                                    ${entry.key}
                                <span class="badge badge-primary badge-pill">${entry.value}</span>
                            </li>
                        </a>
                    </c:forEach>
                </ul>
            </div>
            <!-- Кольори -->
            <div class="py-3">
                <h5 class="font-weight-bold">Кольори</h5>
                <ul class="list-group">
                    <c:forEach items="${colorNamesWithProductAmountMap}" var="entry">
                        <a class="text-decoration-none"
                           href="controller?command=select-by-selector&selector=color&value=${entry.key}">
                            <li class="list-group-item list-group-item-action d-flex
                                                justify-content-between align-items-center category">
                                    ${entry.key}
                                <span class="badge badge-primary badge-pill">${entry.value}</span>
                            </li>
                        </a>
                    </c:forEach>
                </ul>
            </div>
            <!-- Розміри -->
            <div class="py-3">
                <h5 class="font-weight-bold">Розміри</h5>
                <ul class="list-group">
                    <c:forEach items="${sizeNamesWithProductAmountMap}" var="entry">
                        <a class="text-decoration-none"
                           href="controller?command=select-by-selector&selector=size&value=${entry.key}">
                            <li class="list-group-item list-group-item-action d-flex
                                                justify-content-between align-items-center category">
                                    ${entry.key}
                                <span class="badge badge-primary badge-pill">${entry.value}</span>
                            </li>
                        </a>
                    </c:forEach>
                </ul>
            </div>
            <!-- Ціна -->
            <div class="py-3">
                <h5 class="font-weight-bold">Ціна</h5>
                <form method="get">
                    <input type="hidden" name="command" value="select-by-selector"/>
                    <input type="hidden" name="selector" value="price"/>
                    <ul class="list-group">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex justify-content-start col-5">
                                <li class="list-group-item me-1">Від</li>
                                <input class="col-6" type="number" name="from-price" placeholder="0" min="0" max="1000000" step="1">
                            </div>
                            <div class="d-flex justify-content-start col-5">
                                <li class="list-group-item me-1">До</li>
                                <input class="col-6 text-success" type="number" name="to-price"  placeholder="0" min="0" max="1000000" step="1">
                            </div>
                            <input type="submit" class="btn btn-primary" value="OK">
                        </div>

                    </ul>
                </form>
            </div>
        </section>

        <!-- Products Section -->
        <section id="products">
            <div class="container py-3">
                <div class="row">
                    <c:forEach var="product" items="${detailedProducts}">

                        <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 my-3">
                            <div class="card h-100">
                                <a href="controller?command=open-product-info&productId=${product.id}"
                                   class="text-decoration-none">
<%--                                    <img class="card-img-top" src="data:image/jpeg;base64,${product.photo1}">--%>
                                    <img class="card-img-top" src="../design/img/camera.png">
                                </a>
                                <div class="card-body">
                                    <input type="hidden" name="command" value="open-product"/>
                                    <h6 class="font-weight-bold pt-1">${product.name}</h6>
                                    <div class="text-muted description">${product.about}</div>
                                    <div class="d-flex align-items-center product"><span class="fas fa-star"></span>
                                        <span class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                                                class="fas fa-star"></span> <span class="far fa-star"></span></div>
                                    <div class="d-flex align-items-center justify-content-between pt-3">
                                        <div class="d-flex flex-column">
                                            <div class="h6 font-weight-bold">${product.price} Грн.</div>
                                                <%--<div class="text-muted rebate"><strike>48.56</strike></div>--%>
                                        </div>
                                        <a class="btn btn-primary"
                                           href="/controller?command=add-to-cart&productId=${product.id}">В Корзину
                                            <span class="fa fa-shopping-cart"></span></a>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </c:forEach>
                </div>
            </div>
            <div class="wrapper d-flex justify-content-center">
                <a class="btn page-link" href="#">&#8882</a>
                <a class="btn page-link" href="#">1</a>
                <a class="btn page-link" href="#">2</a>
                <a class="btn page-link" href="#">3</a>
                <a class="btn page-link" href="#">&#8883</a>
            </div>
        </section>
    </div>
</div>

<%-- Футер --%>
<%@ include file="parts/footer-full.jsp" %>

<%-- Скрипти --%>
<%@ include file="parts/scripts.jsp" %>

</body>
</html>