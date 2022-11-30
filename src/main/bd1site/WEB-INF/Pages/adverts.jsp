<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/WEB-INF/Css/adverts.css" %>
</style>

<html>
<head>
    <title>Adverts info</title>
</head>
<body>
<h1 class="advert-text">Все объявления</h1>
<div class="post-wrap">
    <c:forEach items="${adverts}" var="advert">
        <div class="post-item">
            <div class="post-item-wrap">
                <a href="/advert?id=${advert.id}" class="post-link">
                    <div class="image-wrapper">
                        <img src="${pathes[advert.id-1]}">
                    </div>
                    <div>
                        <div class="post-cat">${advert.name}
                        </div>
                    </div>
                    <div class="text-wrapper">
                        <h3 class="post-title">Category: ${advert.category}</h3>
                        <h3 class="post-title">Id: ${advert.id}</h3>
                        <h3 class="post-title">User: ${advert.id_user}</h3>
                        <h3 class="post-title">Price: ${advert.price}</h3>
                        <h3 class="post-title">${advert.date}</h3>
                    </div>
                    <div class="overlay">
                    </div>
                </a>
            </div>
        </div>
    </c:forEach>
    <div class="post-item">
        <div class="image">
            <div class="post-item-wrap">
                <a href="/new" class="post-link">
                    <img src="../Images/plus.png">
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
