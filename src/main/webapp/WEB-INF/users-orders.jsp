<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Users Orders"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/orders.css">
</head>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>


<div class="container my-5">
    <h1 class="h3 fw-bold text-center">Замовлення користувачів</h1>
    <h1 class="h3 fw-bold text-center">${errorMessage}</h1>
    <c:set var="errorMessage" scope="session" value="${''}"/>
    <%--    <form action="controller" method="get" class="d-md-flex d-sm-block justify-content-between">--%>
    <%--        <input type="hidden" name="command" value="select-orders">--%>

    <%--        <h1 class="h5 align-self-center col-3">Search Order</h1>--%>

    <%--        <div class="btn-group align-self-center col-12 col-sm-12 col-md-8 col-lg-6">--%>
    <%--            <select name="searchType" class="btn btn-outline-dark col-3 col-sm-3">--%>
    <%--                <option value="orderId">Order ID</option>--%>
    <%--                <option value="created">Created</option>--%>
    <%--                <option value="customer">Customer</option>--%>
    <%--                <option value="status">Status</option>--%>
    <%--            </select>--%>
    <%--            <input type="search" name="searchBy" class="col-6 col-sm-6">--%>
    <%--            <input type="submit" value="Search" class="btn btn-outline-dark col-3 col-sm-3">--%>
    <%--        </div>--%>
    <%--    </form>--%>

    <div class="d-md-flex d-none justify-content-md-between justify-content-sm-center align-content-center
    border-bottom border-2 my-2 p-3 rounded-3" style="background-color: #86bd57!important; color: #1a1e21!important;">
        <div class="col-1 text-sm-center text-md-start align-self-center">
            <h1 class="h5 fw-bold">Order ID</h1>
        </div>
        <div class="col-2 align-self-center">
            <h1 class="h5 fw-bold">Created</h1>
        </div>
        <div class="col-2 align-self-center">
            <h1 class="h5 fw-bold">Customer</h1>
        </div>
        <div class="col-2 align-self-center">
            <h1 class="h5 fw-bold">Show details</h1>
        </div>
        <div class="col-3 align-self-center">
            <h1 class="h5 fw-bold">Status</h1>
        </div>
    </div>


    <c:forEach var="order" items="${allUsersOrders}">

        <div class="d-md-flex d-sm-block justify-content-md-between justify-content-sm-center
    text-center border-bottom border-2 my-2 p-2 rounded-3 text-light" style="background-color: rgba(0,0,0,0.68)">
            <div class="col-md-1 text-sm-center text-md-start align-self-center my-2">
                <h1 class="h5">${order.id}</h1>
            </div>
            <div class="col-md-2 text-sm-center text-md-start align-self-center my-2">
                <h1 class="h5">${order.getFormattedCreateTime()}</h1>
            </div>
            <div class="col-md-2 text-sm-center text-md-start align-self-center my-2">
                <h1 class="h5">${order.getNameOfOrderOwner()}</h1>
            </div>
            <div class="col-md-2 text-sm-center text-md-start align-self-center my-2">
                <button type="button" class="mb-0 btn btn-dark w-auto"
                        data-toggle="modal" data-target="#details-about-order${order.id}">Details
                </button>
                <div class="modal fade" id="details-about-order${order.id}" tabindex="-1" role="dialog"
                     aria-labelledby="labelEdit" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content text-dark">
                            <div class="modal-header border-bottom-0">
                                <div>
                                    <h1 class="h5 modal-title">ID: ${order.id}</h1>
                                    <h1 class="h5 modal-title">Замовник: ${order.getNameOfOrderOwner()}</h1>
                                    <h1 class="h5 modal-title">Дата створення: ${order.getFormattedCreateTime()}</h1>
                                </div>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="card my-2 mx-2 bg-light">
                                <div class="card-body w-100">
                                    <div class="d-flex justify-content-between px-2"
                                         style="background-color: #86bd57!important;">
                                        <div class="col-6">
                                            <h1 class="h5 fw-bold">Name</h1>
                                        </div>
                                        <div class="col-3">
                                            <h1 class="h5 fw-bold">Price</h1>
                                        </div>
                                        <div class="col-3">
                                            <h1 class="h5 fw-bold">Amount</h1>
                                        </div>
                                    </div>
                                    <c:forEach var="oProduct" items="${order.products}">
                                        <div class="d-flex justify-content-between bg-white px-2 mt-2 mb-2">
                                            <div class="col-6 align-self-center">
                                                <h1 class="h5">${oProduct.getProduct().getName()}</h1>
                                            </div>
                                            <div class="col-3 align-self-center">
                                                <h1 class="h5">${oProduct.getProduct().getPrice().doubleValue()}
                                                    UAH</h1>
                                            </div>
                                            <div class="col-3 align-self-center">
                                                <h1 class="h5">x ${oProduct.productAmount}</h1>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <div class="d-flex justify-content-between bg-white px-2 mt-2 mb-2">
                                        <div class="col-12 align-self-center">
                                            <h1 class="h5 text-danger">Всього: ${order.totalOrderPrice.doubleValue()}
                                                Грн.</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 text-sm-center text-md-start align-self-center my-2">
                <form method="get" action="controller" class="d-flex btn-group">
                    <input type="hidden" name="command" value="change-order-status">
                    <input type="hidden" name="orderId" value="${order.id}">
                    <c:choose>
                        <c:when test="${order.statusId == 1}">
                            <c:set var="statusColor" scope="request" value="${'text-warning btn-dark'}"/>
                            <c:set var="selected" scope="page" value="1"/>
                        </c:when>
                        <c:when test="${order.statusId == 2}">
                            <c:set var="statusColor" scope="request" value="${'text-success btn-dark'}"/>
                            <c:set var="finalState" scope="request" value="${'disabled'}"/>
                            <c:set var="selected" scope="page" value="2"/>
                        </c:when>
                        <c:when test="${order.statusId == 3}">
                            <c:set var="statusColor" scope="request" value="${'text-danger btn-dark'}"/>
                            <c:set var="finalState" scope="request" value="${'disabled'}"/>
                            <c:set var="selected" scope="page" value="3"/>
                        </c:when>
                    </c:choose>
                    <select name="statusId" class="btn ${statusColor} fw-bold col-8" ${finalState}>
                        <c:choose>
                            <c:when test="${order.statusId == 1}">
                                <option value="${order.statusId}" selected="${selected==1?'selected':''}"
                                        class="form-select-button">REGISTERED
                                </option>
                                <option value="2" class="form-select-button">PAID
                                </option>
                                <option value="3" class="form-select-button">CANCELED
                                </option>
                            </c:when>
                            <c:when test="${order.statusId == 2}">
                                <option value="${order.statusId}" selected="${selected==2?'selected':''}"
                                        class="form-select-button">PAID
                                </option>
                                <option value="1" class="form-select-button">REGISTERED
                                </option>
                                <option value="3" class="form-select-button">CANCELED
                                </option>
                            </c:when>
                            <c:when test="${order.statusId == 3}">
                                <option value="${order.statusId}" selected="${selected==3?'selected':''}"
                                        class="form-select-button">CANCELED
                                </option>
                                <option value="1" class="form-select-button">REGISTERED
                                </option>
                                <option value="2" class="form-select-button">PAID
                                </option>
                            </c:when>
                        </c:choose>
                    </select>
                    <input type="submit" class="btn ${statusColor} col-2" value="OK" ${finalState}>
                    <c:set var="finalState" scope="request" value="${''}"/>
                </form>
            </div>
        </div>

    </c:forEach>

</div>


<%--        <div class="col-md-2 text-sm-center text-md-start align-self-center">--%>
<%--            <h1 class="h5 text-warning">IN PROGRESS</h1>--%>
<%--        </div>--%>

<%-- Футер --%>
<%--<%@ include file="/WEB-INF/parts/footer-light.jsp" %>--%>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>