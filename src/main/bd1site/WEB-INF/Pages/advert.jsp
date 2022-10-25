<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<style><%@include file="/WEB-INF/Css/adverts.css"%></style>

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
    <c:if test="${not empty advert.picture_ref}">
        <div class="advert-image">
            <img src="${advert.picture_ref}">
        </div>
    </c:if>
</div>
</body>
</html>