<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<head>
    <c:url value="/resources/css" var="table_css"/>
    <c:url value="/resources/js" var="table_js"/>

    <link href="${table_css}/table.css" rel="stylesheet" type="text/css">

    <script src="${table_js}/jquery-3.0.0.js" type="text/javascript"></script>
    <script src="${table_js}/currencyViewer.js" type="text/javascript"></script>
</head>