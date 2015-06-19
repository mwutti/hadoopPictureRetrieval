<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 11/06/15
  Time: 08:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="mostSimilar" type="java.util.List<java.lang.String>" scope="request"/>
<html>
<head>
    <title>result</title>
</head>
<body>
<div id="results">
    <c:forEach items="${mostSimilar}" var="similar" varStatus="i">
        <img src="<c:url value="/getFile/?src=${similar}"/>"/>
    </c:forEach>
</div>

</body>
</html>
