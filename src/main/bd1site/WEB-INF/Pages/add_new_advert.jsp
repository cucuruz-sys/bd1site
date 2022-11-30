<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/WEB-INF/Css/adverts.css" %>
</style>
<html>
<head>
    <title>Добавление объявления</title>
</head>
<body>
<h1 style="display: flex; justify-content: center;">Добавление нового объявления</h1>
<form method="post" action="new">
    <div class="edit-advert">
        <div class="edit-advert-text">
            <div class="advert-text">
                Название: <input type="text" name="name"/>
            </div>
            <div class="advert-text">
                Категория:
                <select name="categoryId">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}" ${"Musor" == category.name ? 'selected' : ''}>
                                ${category.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="advert-text">
                Id пользователя: <input type="number" name="id_user"/>
            </div>
            <div class="advert-text">
                Цена: <input type="number" name="price"/>
            </div>
            <div class="advert-text">
                Дата создания: <input type="date" name="date"/>
            </div>
            <div class="advert-text">
                Описание: <textarea name="description" style="height:6em;width:250px;"></textarea>
            </div>
        </div>
        <div class="advert-image">
            <img src="${path}">
        </div>
        <div class="edit-image">
            <a href="/1" class="post-link">
                <img src="../Images/edit.png">
            </a>
        </div>
        <input type="submit" value="Сохранить">
    </div>
</form>
</body>
</html>
