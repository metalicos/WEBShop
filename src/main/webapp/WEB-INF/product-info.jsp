<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Shopping Cart"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/productInfo.css">

</head>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>


<div class="container-fluid mt-5">
    <div class="row">
        <div class="col-md-5">
            <div class="carousel slide" data-ride="carousel" id="carousel-1">
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <img class="img-thumbnail w-100 d-block"src="data:image/jpeg;base64,${productToShow.photo1}"
                             alt="Slide Image" loading="lazy">
                    </div>
                    <div class="carousel-item">
                        <img class="img-thumbnail w-100 d-block"src="data:image/jpeg;base64,${productToShow.photo2}"
                             alt="Slide Image" loading="lazy">
                    </div>
                    <div class="carousel-item">
                        <img class="img-thumbnail w-100 d-block"src="data:image/jpeg;base64,${productToShow.photo3}"
                             alt="Slide Image" loading="lazy">
                    </div>
                </div>
                <div>
                    <a class="carousel-control-prev" href="#carousel-1" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carousel-1" role="button" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
                <ol class="carousel-indicators">
                    <li data-target="#carousel-1" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-1" data-slide-to="1"></li>
                    <li data-target="#carousel-1" data-slide-to="2"></li>
                </ol>
            </div>
        </div>
        <div class="col-md-7">
            <h4>${productToShow.name}</h4>
            <div class="price">
                <span class="mr-2 text-success">${productToShow.price} Грн.</span>
<%--                <span class="mr-2 cut">65,000</span>--%>
<%--                <span class="text-success">25% OFF</span>--%>
            </div>
            <h1 class="h4 mt-4 fw-bold">Загальний опис</h1>
            <div>
                <span>${productToShow.about}</span>
            </div>
            <h1 class="h4 mt-4 fw-bold">Детальна характеристика товару</h1>
            <div class="d-flex justify-content-start">
                <span class="font-weight-bold">Категорія товару: </span>
                <span class="px-2 text-grey-bold">${productToShow.categoryName}</span>
            </div>
            <div class="d-flex justify-content-start">
                <span class="font-weight-bold">Колір товару: </span>
                <span class="px-2 text-grey-bold">${productToShow.color}</span>
            </div>
            <div class="d-flex justify-content-start">
                <span class="font-weight-bold">Розмір товару: </span>
                <span class="px-2 text-grey-bold">${productToShow.size}</span>
            </div>
            <div class="d-flex justify-content-start">
                <span class="font-weight-bold">Категорія товару: </span>
                <span class="px-2 text-grey-bold">${productToShow.categoryName}</span>
            </div>
            <hr>
            <div>
                <a class="btn btn-styled btn-orange mr-2"
                   href="controller?command=add-to-cart&productId=${productToShow.id}">В корзину</a>
                <a class="btn btn-styled btn-primary" href="#">Купити зараз</a>
            </div>
        </div>
    </div>
</div>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>