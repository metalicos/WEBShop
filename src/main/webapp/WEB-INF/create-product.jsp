<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Create Product"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/createProduct.css">
</head>

<body>
<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>


<form enctype="multipart/form-data" method="post" action="controller"
      class="container col-sm-10 col-md-6 my-4">
    <input type="hidden" name="command" value="create-product">

    <h1 class="h3 text-center">
        <span class="fw-bold text-decoration">Додати товар</span>
    </h1>
    <h1 class="h4 text-danger fw-bold text-danger text-center">${errorMessage}</h1>
    <div class="form-group">
        <h1 class="h5">Назва товару</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="pNameUa" placeholder="Назва товару українською"
                       class="form-control input"/>
            </div>
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="pNameEn" placeholder="Product name in English"
                       class="form-control input"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <h1 class="h5">Опис товару українською</h1>
        <textarea name="pAboutUa" cols="30" rows="3" class="text-area w-100 text-field"
                  style="height: 100px;min-height: 40px;"></textarea>
    </div>
    <div class="form-group">
        <h1 class="h5">Product description</h1>
        <textarea name="pAboutEn" cols="30" rows="3" class="text-area w-100 text-field"
                  style="height: 100px;min-height: 40px;"></textarea>
    </div>


    <div class="form-group">
        <h1 class="h5">Колір товару</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="pColorUa" placeholder="Колір товару українською"
                       class="form-control input"/>
            </div>
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="pColorEn" placeholder="Product color in English"
                       class="form-control input"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <h1 class="h5">Розмір товару</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <h1 class="h3 text-start mx-2 text-dark">L:</h1>
            <div class="input-group">
                <div class="col-6">
                    <input type="number" placeholder="0.00" name="pSizeWidth" min="0" value="0" step="0.01"
                           class="form-control input" style="height: 40px"/>
                </div>
                <select name="mSizeWidth" class="form-select rounded-3" style="height: 40px">
                    <option value="mm">mm/мм</option>
                    <option value="cm">cm/см</option>
                    <option value="m">m/м</option>
                </select>
            </div>
            <h1 class="h3 text-start mx-2 text-dark">W:</h1>
            <div class="input-group">
                <div class="col-6">
                    <input type="number" placeholder="0.00" name="pSizeHeight" min="0" value="0" step="0.01"
                           class="form-control input" style="height: 40px"/>
                </div>
                <select name="mSizeHeight" class="form-select rounded-3" style="height: 40px">
                    <option value="mm">mm/мм</option>
                    <option value="cm">cm/см</option>
                    <option value="m">m/м</option>
                </select>
            </div>
            <h1 class="h3 text-start mx-2 text-dark">H:</h1>
            <div class="input-group">
                <div class="col-6">
                    <input type="number" placeholder="0.00" name="pSizeDepth" min="0" value="0" step="0.01"
                           class="form-control input" style="height: 40px"/>
                </div>
                <select name="mSizeDepth" class="form-select rounded-3" style="height: 40px">
                    <option value="mm">mm/мм</option>
                    <option value="cm">cm/см</option>
                    <option value="m">m/м</option>
                </select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <h1 class="h5">Виберіть категорію</h1>
        <select name="categoryId" class="form-select-md w-100" style="height: 40px;">
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}">${category.nameUa}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group d-flex align-content-center justify-content-start my-2">
        <h1 class="h5 align-self-center">Немає потрібної категорії?</h1>
        <a class="btn btn-outline-dark border-2 mx-4"
           href="controller?command=open-create-category"
           target="_blank">Добавити категорію</a>
    </div>

    <div class="form-group">
        <h1 class="h5">Ціна товару</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <input type="number" name="productPrice" placeholder="0.0" min="0.0" value="0" step="0.1"
                   class="form-control input"/>
        </div>
    </div>

    <div class="form-group">
        <h1 class="h5">Кількість товарів, що додаються</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <input type="number" name="productAmount" placeholder="0" min="0" value="0" step="1"
                   class="form-control input"/>
        </div>
    </div>

    <div class="py-2 justify-content-between">
        <h1 class="h5">Головна фотографія</h1>
        <input class="text-dark h5" type="file" name="photo1">

        <h1 class="h5">Додаткова фотографія</h1>
        <input class="text-dark h5" type="file" name="photo2">

        <h1 class="h5">Додаткова фотографія</h1>
        <input class="text-dark h5" type="file" name="photo3">
    </div>
    <input type="submit" class="btn btn-dark text-center py-2 my-2 w-100" value="Створити"/>
</form>


<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>

<script src="../design/js/createProduct.js"></script>

</body>
</html>