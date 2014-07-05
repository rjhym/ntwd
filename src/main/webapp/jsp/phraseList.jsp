<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

</head>
<body>
<div class="container">
    <h3 class="page-header">成语列表</h3>

    <div class="row">
        <c:forEach items="${phrases}" var="phrase">
            <div class="col-md-2"><a href="/ntwd/phrase/d-i-${phrase.id}.jsp" target="_blank">${phrase.phrase}</a></div>
        </c:forEach>
    </div>

    <c:set var="curPage" value="${page}" />
    <c:set var="totalPage" value="${total/rows+1}" />

    <c:forEach var = "li" begin="1" end ="${totalPage}">
        <a href="/ntwd/phrase/l-100-${li}.jsp">第${li}页</a>
    </c:forEach>

</div>
<!-- Latest compiled and minified JavaScript -->
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>
