<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/WEB-INF/Css/adverts.css" %>
</style>

<html>
<head>
    <title>Детальная информация об объявлении ${advert.name}</title>
</head>
<body>
<div class="advert">
    <h class="advert-text">${advert.name}</h>
    <h class="advert-text">Category: ${advert.category}</h>
    <h class="advert-text">Id: ${advert.id}</h>
    <h class="advert-text">User: ${advert.id_user}</h>
    <h class="advert-text">Price: ${advert.price}</h>
    <h class="advert-text">${advert.date}</h>
    <h class="advert-text">${advert.description}</h>
    <div class="edit-image">
        <a href="/advert/edit?id=${advert.id}" class="post-link">
            <img src="../Images/edit.png">
        </a>
    </div>
    <div class="advert-image">
        <img src="${path}">
    </div>
    <form method="post" type="submit">
        <button name="del" value="submit">
            <div class="edit-image">
                <img src="../Images/delete.png">
            </div>
        </button>
    </form>
</div>
</body>
</html>