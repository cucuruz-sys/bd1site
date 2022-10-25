<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style><%@include file="/WEB-INF/Css/adverts.css"%></style>
<html>
<head>
  <title>Adverts info</title>
</head>
<body>
<c:forEach items="${adverts}" var="advert">
  <div class="post-wrap">
    <div class="post-item">
      <div class="post-item-wrap">
        <a href="/advert?id=${advert.id}" class="post-link">
          <div class="image-wrapper">
            <img src="${advert.picture_ref}">
          </div>
          <div class="text-wrapper">
            <div class="post-cat">${advert.name}</div>
            <h3 class="post-title">${advert.date}</h3>
          </div>
          <div class="overlay">
          </div>
        </a>
      </div>
    </div>
  </c:forEach>
</body>
</html>
