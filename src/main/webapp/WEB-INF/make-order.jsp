<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | New Order"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/newOrder.css">
</head>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>

<form action="controller" method="get">
    <input type="hidden" name="command" value="make-order">

    <div class="container my-4">

        <h1 class="h4 text-center">${detailedAccount.firstNameUa} ${detailedAccount.patronymicUa}, thanks for your
            order!</h1>
        <div class="d-lg-flex d-md-block justify-content-sm-center justify-content-md-between align-content-center">

            <div class="col-md-12 col-lg-4 col-12">
                <div class="card bg-light my-2 mx-2">
                    <div class="card-body">
                        <h1 class="h4">Your information:</h1>
                        <h1 class="h5"><i
                                class="fa fa-user-circle me-2"></i>${detailedAccount.surnameUa} ${detailedAccount.firstNameUa} ${detailedAccount.patronymicUa}
                        </h1>
                        <h1 class="h5"><i class="fa fa-phone me-2"></i>${detailedAccount.phone}</h1>
                        <h1 class="h5"><i class="fa fa-envelope me-2"></i>${detailedAccount.email}</h1>
                    </div>
                </div>

                <div class="card bg-light my-2 mx-2">
                    <div class="card-body">
                        <h1 class="h4">Your profile address:</h1>
                        <h1 class="h5"><i
                                class="fa fa-map-marker-alt me-2"></i>${detailedAccount.countryUa}, ${detailedAccount.cityUa},
                        </h1>
                        <h1 class="h5">${detailedAccount.streetUa} ${detailedAccount.buildingUa}, ${detailedAccount.flatUa}</h1>
                        <h1 class="h5">Zip-code: ${detailedAccount.zipCode}</h1>
                    </div>
                </div>

                <div class="card my-2 mx-2" style="background-color: rgba(246,204,30,0.26)">
                    <div class="card-body">
                        <h1 class="h5 text-start">If your information has changed, please update it in your
                            <a href="controller?command=open-profile-page" target="_blank">profile</a></h1>
                    </div>
                </div>
            </div>

            <div class="d-block col-md-12 col-lg-8 col-12">
                <div class="card my-2 mx-2 bg-light">
                    <div class="card-body w-100">
                        <h1 class="h4">Your Products:</h1>
                        <div class="d-flex justify-content-between px-2" style="background-color: #86bd57!important;">
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

                        <c:forEach items="${userShoppingCart}" var="entry">
                            <div class="d-flex justify-content-between bg-white px-2 mt-2 mb-2">
                                <div class="col-6 align-self-center">
                                    <h1 class="h5">${entry.key.name}</h1>
                                </div>
                                <div class="col-3 align-self-center">
                                    <h1 class="h5">${entry.key.price} UAH</h1>
                                </div>
                                <div class="col-3 align-self-center">
                                    <h1 class="h5">x ${entry.value}</h1>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="card my-2 mx-2 bg-light">
                    <div class="card-body w-100">
                        <h1 class="h4">Choose delivery:</h1>
                        <c:forEach var="delivery" items="${deliveries}">
                            <c:choose>
                                <c:when test="${delivery.id == chosenDelivery.id}">
                                    <a class="btn btn-success col-5 my-1 mx-1" style="background-color: #86bd57!important; color: #1a1e21"
                                       href="controller?command=choose-delivery&deliveryId=${delivery.id}">
                                            ${delivery.nameUa} ${delivery.averageDeliveryTime}
                                        дні ${delivery.deliveryPrice.doubleValue()} грн
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-outline-dark col-5 my-1 mx-1"
                                       href="controller?command=choose-delivery&deliveryId=${delivery.id}">
                                            ${delivery.nameUa} ${delivery.averageDeliveryTime}
                                        дні ${delivery.deliveryPrice.doubleValue()} грн
                                    </a>
                                </c:otherwise>



                            </c:choose>
                        </c:forEach>
                    </div>
                </div>

                <div class="card my-2 mx-2 bg-light">
                    <div class="card-body w-100">
                        <div class="d-flex justify-content-between">
                            <h1 class="h5 fw-light">Products price:</h1>
                            <h1 class="h6">${totalProductSum} UAH</h1>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h1 class="h5 fw-light">Delivery price:</h1>
                            <h1 class="h6">${chosenDelivery.deliveryPrice} UAH</h1>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h1 class="h4 text-danger ">Totally:</h1>
                            <h1 class="h5 text-danger fw-3">${totalProductSum+chosenDelivery.deliveryPrice} UAH</h1>
                        </div>
                    </div>
                    <div class="d-flex justify-content-between my-2 card-body">
                        <a href="controller?command=open-home-page" class="btn btn-outline-dark col-5">
                            <h1 class="h5 my-auto py-1">
                                <i class="fa fa-times"></i>
                                Cancel</h1>
                        </a>
                        <a href="controller?command=make-order" class="btn btn-outline-success col-6">
                            <h1 class="h5 my-auto py-1">Approve order
                                <i class="fa fa-check"></i>
                            </h1>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>
</body>
</html>