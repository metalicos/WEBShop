<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Orders"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/orders.css">
</head>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>


<div class="container my-5">


<%--    <form action="controller" method="get" class="d-md-flex d-sm-block justify-content-between">--%>
<%--        <input type="hidden" name="command" value="select-orders">--%>

<%--        <h1 class="h5 align-self-center col-3">Search Order</h1>--%>

<%--        <div class="btn-group align-self-center col-12 col-sm-12 col-md-8 col-lg-6">--%>
<%--            <select name="searchType" class="btn btn-outline-dark col-3 col-sm-3">--%>
<%--                <option value="orderId">Order ID</option>--%>
<%--                <option value="created">Created</option>--%>
<%--                <option value="status">Status</option>--%>
<%--            </select>--%>
<%--            <input type="search" name="searchBy" class="col-6 col-sm-6">--%>
<%--            <input type="submit" value="Search" class="btn btn-outline-dark col-3 col-sm-3">--%>
<%--        </div>--%>
<%--    </form>--%>

    <div class="d-md-flex d-none justify-content-md-between justify-content-sm-center align-content-center
    border-bottom border-2 my-2 text-light p-3 rounded-3"
         style="background-color: #86bd57!important; color: #1a1e21!important;">
        <div class="col-2 text-sm-center text-md-start align-self-center px-2">
            <h1 class="h5 fw-bold">Order ID</h1>
        </div>
        <div class="col-2 align-self-center">
            <h1 class="h5 fw-bold">Created</h1>
        </div>
        <div class="col-3 align-self-center">
            <h1 class="h5 fw-bold">Show details</h1>
        </div>
        <div class="col-3 align-self-center">
            <h1 class="h5 fw-bold">Status</h1>
        </div>
        <div class="col-2 align-self-center">
            <h1 class="h5 fw-bold">Total Price</h1>
        </div>
    </div>

    <c:forEach var="order" items="${userOrders}">

        <div class="d-md-flex d-sm-block justify-content-md-between justify-content-sm-center
    text-center border-bottom border-2 my-2 bg-light p-2 rounded-3">
            <div class="col-md-2 text-sm-center text-md-start align-self-center my-2 px-2">
                <h1 class="h6">${order.id}</h1>
            </div>
            <div class="col-md-2 text-sm-center text-md-start align-self-center my-2">
                <h1 class="h6">${order.getFormattedCreateTime()}</h1>
            </div>
            <div class="col-md-3 text-sm-center text-md-start align-self-center my-2">
                <button type="button" class="mb-0 btn btn-outline-warning text-dark w-auto"
                        data-toggle="modal" data-target="#details-about-order${order.id}">Details
                </button>
                <div class="modal fade" id="details-about-order${order.id}" tabindex="-1" role="dialog"
                     aria-labelledby="labelEdit" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header border-bottom-0">
                                <h5 class="modal-title" id="labelEdit">Your Products:</h5>
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
                                            <h1 class="h5 text-danger">Всього: ${order.totalOrderPrice.doubleValue()} Грн.</h1>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 text-sm-center text-md-start align-self-center my-2">
                <c:choose>
                    <c:when test="${order.statusId == 1}">
                        <h1 class="h6 text-warning fw-bold">REGISTERED</h1>
                    </c:when>
                    <c:when test="${order.statusId == 2}">
                        <h1 class="h6 text-success fw-bold">PAID</h1>
                    </c:when>
                    <c:when test="${order.statusId == 3}">
                        <h1 class="h6 text-danger fw-bold">CANCELED</h1>
                    </c:when>
                </c:choose>
            </div>
            <div class="col-md-2 text-sm-center text-md-start align-self-center my-2 px-2">
                <h1 class="h6">${order.totalOrderPrice.doubleValue()} Грн.</h1>
            </div>
        </div>

    </c:forEach>
</div>


<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>