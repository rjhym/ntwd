<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <div class="row">
        <table class="table table-border">
            <tr>
                <td>成语</td>
                <td>${phrase.phrase}</td>
            </tr>
            <tr>
                <td>拼音</td>
                <td>${phrase.phraseSpell}</td>
            </tr>
            <tr>
                <td>解释</td>
                <td>${phrase.phraseParaphrase}</td>
            </tr>
            <tr>
                <td>出处</td>
                <td>${phrase.phraseProvenance}</td>
            </tr>
            <tr>
                <td>示例</td>
                <td>${phrase.phraseDemo}</td>
            </tr>
        </table>
    </div>
</div>
<!-- Latest compiled and minified JavaScript -->
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>
