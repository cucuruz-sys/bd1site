<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/WEB-INF/Css/adverts.css" %>
</style>
<html>
<head>
    <title>Редактор объявления ${advert.id}</title>
</head>
<body>
<h1 style="display: flex; justify-content: center;">Редактирование информации об объявлении "${advert.id}"</h1>
<form method="post" action="edit">
    <div class="edit-advert">
        <div class="edit-advert-text">
            <input type="hidden" name="id" value="${advert.id}"/>
            <div class="advert-text">
                Название: <input type="text" name="name" value="${advert.name}"/>
            </div>
            <div class="advert-text">
                Категория:
                <select name="categoryId">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}" ${advert.category == category.name ? 'selected' : ''}>
                                ${category.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="advert-text">
                Id пользователя: <input type="number" name="id_user" value="${advert.id_user}"/>
            </div>
            <div class="advert-text">
                Цена: <input type="number" name="price" value="${advert.price}"/>
            </div>
            <div class="advert-text">
                Дата создания: <input type="date" name="date" value="${advert.date}"/>
            </div>
            <div class="advert-text">
                Описание: <textarea name="description" style="height:6em;width:250px;">${advert.description}</textarea>
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
