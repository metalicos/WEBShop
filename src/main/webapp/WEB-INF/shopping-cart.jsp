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


<div class="container my-5">
    <h1 class="h4 text-danger text-center">${errorMessage}</h1>
    <c:set var="errorMessage" scope="session" value="${''}"/>
    <table id="cart" class="table table-hover table-condensed">

        <c:choose>
            <c:when test="${userShoppingCart.size() == 0 || userShoppingCart == null}">
                <tbody>
                <tr>
                    <td colspan="5" class="justify-content-center">
                        <h1 class="h3 text-center">Здається корзина порожня</h1>
                        <img class="align-self-center card-img" src="/design/img/cartIsEmpty.svg" width="200"
                             height="200">
                        <h1 class="h6 text-center" style="color: rgba(0,0,0,0.36)">Але це ніколи не пізно виправити :)</h1>
                    </td>
                </tr>
                </tbody>

            </c:when>

            <c:otherwise>
                <thead>
                <tr>
                    <th style="width:50%">Товар</th>
                    <th style="width:10%">Ціна</th>
                    <th style="width:8%">Кількість</th>
                    <th style="width:22%" class="text-center">Підсумок</th>
                    <th style="width:10%"></th>
                </tr>
                </thead>
                <c:forEach items="${userShoppingCart}" var="entry">
                    <tbody>
                    <tr>
                        <td data-th="Товар">
                            <div class="row">
                                <div class="col-sm-2 hidden-xs">
                                    <img src="data:image/jpeg;base64,${entry.key.photo1}" alt="..."
                                         class="img-responsive " width="100" height="100"/>
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
                        <td data-th="Підсумок" class="text-center fw-bold">${entry.key.price*entry.value} Грн.
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

                <tfoot>
                <tr>
                    <td><a href="/" class="btn btn-warning"><i class="fa fa-angle-left"></i> Продовжити покупки</a></td>
                    <td colspan="2" class="hidden-xs"></td>
                    <td class="hidden-xs text-center"><strong>Всього до сплати:
                        <span class="text-danger">${totalProductSum} Грн.</span>
                    </strong></td>
                    <c:choose>
                        <c:when test = "${account != null}">
                            <td><a href="controller?command=open-make-order-page" class="btn btn-success btn-block">Купити <i class="fa fa-angle-right"></i></a></td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <a href="controller?command=open-login-page" class="btn btn-success btn-block">Купити
                                    <i class="fa fa-angle-right"></i></a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                </tfoot>
            </c:otherwise>
        </c:choose>
    </table>
    <c:if test="${userShoppingCart.size() == 0 || userShoppingCart == null}">
        <div class="d-flex align-content-center justify-content-center">
        <a href="controller?command=open-home-page" class="btn btn-success w-25">
            <i class="fa fa-arrow-left pe-2"></i>На головну
        </a>
        </div>
    </c:if>
</div>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>