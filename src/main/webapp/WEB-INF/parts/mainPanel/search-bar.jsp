<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- Пошукова стрічка -->
<form class="col-lg-3 col-xl-4 align-self-center" action="controller" method="get">
    <input type="hidden" name="command" value="select-by-selector"/>
    <input type="hidden" name="selector" value="search-bar"/>
    <div class="input-group">
        <button type="button" class="input-group-text btn-group">
            <span class="fas fa-search"></span>
        </button>
        <input type="text" class="form-control" placeholder="Я шукаю..." aria-label="Шукати"
               aria-describedby="basic-addon1" name="value">
    </div>
</form>