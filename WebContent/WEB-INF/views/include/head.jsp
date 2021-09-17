<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- el태그 활용 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<meta charset="UTF-8">

<!-- reset.css -->
<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
<!-- webutil.js -->
<script type="text/javascript" src="${contextPath}/resources/js/webUtil.js"></script>
<!-- fontawesome -->
<script src="https://kit.fontawesome.com/6fd6b71dc1.js" crossorigin="anonymous"></script>

<!-- fullcalendar.js -->
<script src='${contextPath}/resources/js/schedule/main.js'></script>
<script src='${contextPath}/resources/js/schedule/locales-all.js'></script>

<!-- header -->
<link rel="stylesheet" href="${contextPath}/resources/css/header/header.css">
<script defer src="${contextPath}/resources/js/header/gnb.js"></script>


