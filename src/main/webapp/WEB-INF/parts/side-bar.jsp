<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


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
                        <input class="col-8" type="number" name="from-price" placeholder="0" min="0.0"
                               max="1000000" step="1">
                    </div>

                    <div class="d-flex justify-content-start col-5">
                        <li class="list-group-item me-1">До</li>
                        <input class="col-8 text-success" type="number" name="to-price" placeholder="1000000" min="0"
                               max="1000000" step="1">
                    </div>

                    <input type="submit" class="btn btn-primary" value="OK">
                </div>

            </ul>
        </form>
    </div>

</section>
