<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- Корзина -->
<a href="controller?command=open-cart-page" class="d-flex align-items-center text-dark">
    <h5>
        <span class="fas fa-shopping-cart"></span>
    </h5>
    <c:if test="${productsInCart!=null}">
        <span class="badge bg-danger">${productsInCart}</span>
    </c:if>
</a>