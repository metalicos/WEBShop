<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Shopping Cart"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/shoppingCart.css">
</head>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>


<div class="container mt-3">
    <table id="cart" class="table table-hover table-condensed">
        <thead>
        <tr>
            <th style="width:50%">Товар</th>
            <th style="width:10%">Ціна</th>
            <th style="width:8%">Кількість</th>
            <th style="width:22%" class="text-center">Підсумок</th>
            <th style="width:10%"></th>
        </tr>
        </thead>

        <c:choose>
            <c:when test="${userShoppingCart.size() == 0 || userShoppingCart == null}">
                <tbody>
                <tr>
                    <td colspan="5" class="justify-content-center">
                        <h1 class="h3 text-center">Здається корзина порожня</h1>
                        <img class="align-self-center card-img" src="/design/img/cartIsEmpty.svg" width="200" height="200">
                    </td>
                </tr>
                </tbody>
            </c:when>

            <c:otherwise>
                <c:forEach items="${userShoppingCart}" var="entry">
                    <tbody>
                    <tr>
                        <td data-th="Товар">
                            <div class="row">
                                <div class="col-sm-2 hidden-xs">
                                    <img src="http://placehold.it/100x100" alt="..." class="img-responsive "/>
                                </div>
                                <div class="col-sm-10">
                                    <h4 class="nomargin">${entry.key.name}</h4>
                                    <p>${entry.key.about}</p>
                                </div>
                            </div>
                        </td>
                        <td data-th="Ціна">${entry.key.price} Грн.</td>
                        <td data-th="Кількість">
                                ${entry.value}
                        </td>
                        <td data-th="Підсумок" class="text-center fw-bold"> Грн.
                        </td>
                        <td data-th="Видалити" class="align-content-start">
                            <a class="btn btn-danger"
                               href="controller?command=delete-from-cart&productId=${entry.key.id}">
                                <i class="fa fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                    </tbody>

                </c:forEach>
            </c:otherwise>
        </c:choose>


        <%--        userShoopingCart--%>

        <tfoot>
        <tr>
            <td><a href="/" class="btn btn-warning"><i class="fa fa-angle-left"></i> Продовжити покупки</a></td>
            <td colspan="2" class="hidden-xs"></td>
            <td class="hidden-xs text-center"><strong>Всього до сплати:
                <span class="text-danger">${totalProductSum} Грн.</span>
            </strong></td>
            <td><a href="#" class="btn btn-success btn-block">Купити <i class="fa fa-angle-right"></i></a></td>
        </tr>
        </tfoot>

    </table>
</div>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>