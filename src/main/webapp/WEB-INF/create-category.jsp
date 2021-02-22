<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Create Category"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/createProduct.css">
</head>

<body>
<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>

<form class="form-horizontal container col-6 my-4" method="get">
    <input type="hidden" name="command" value="create-category"/>


    <h1 class="h3 text-center"><span class="fw-bold text-decoration">Створити категорію</span></h1>

    <div class="form-group">
        <h1 class="h5">Назва категорії</h1>
        <h1 class="h5 text-danger">${failErrorMessage}</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="сNameUa" placeholder="Назва категорії українською" value="${categoryNameUa}"
                       class="form-control input" required/>
            </div>
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="сNameEn" placeholder="Category name in English" value="${categoryNameEn}"
                       class="form-control input" required/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <h1 class="h5">Опис категорії українською</h1>
        <textarea name="cAboutUa" cols="30" rows="3" class="text-area w-100 text-field" required
                  style="height: 100px;min-height: 40px;" >${categoryAboutUa}</textarea>
    </div>
    <div class="form-group">
        <h1 class="h5">Category description</h1>
        <textarea name="cAboutEn" cols="30" rows="3" class="text-area w-100 text-field" required
                  style="height: 100px;min-height: 40px;">${categoryAboutEn}</textarea>
    </div>


    <input type="submit" class="btn btn-dark text-center py-2 my-4 w-100" value="Створити"/>
</form>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>