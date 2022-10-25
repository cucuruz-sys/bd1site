<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Детальная информация об объявлении ${advert.name}</title>
</head>
<body>
<h1>${advert.name}</h1> <span>${advert.date}</span>
<div>Цена: ${advert.price}</div>
<div>Категория: ${advert.category}</div>
<div>Id пользователя: ${advert.id_user}</div>
<c:if test="${not empty advert.picture_ref}">
    <div>
        <img src="${advert.picture_ref}">
    </div>
</c:if>
</body>
</html>
