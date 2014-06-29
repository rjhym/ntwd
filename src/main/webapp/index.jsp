<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <form role="form" action="/ntwd/admin/phrase/add.json" method="post">
        <div class="form-group">
            <label for="phrase">成语</label>
            <input type="text" class="form-control" id="phrase" name="phrase" placeholder="成语">
        </div>
        <div class="form-group">
            <label for="phraseSpell">成语拼音</label>
            <input type="text" class="form-control" id="phraseSpell" name="phraseSpell" placeholder="成语拼音">
        </div>
        <div class="form-group">
            <label for="phraseParaphrase">成语拼音</label>
            <input type="text" class="form-control" id="phraseParaphrase" name="phraseParaphrase" placeholder="成语释义">
        </div>
        <div class="form-group">
            <label for="phraseProvenance">成语拼音</label>
            <input type="text" class="form-control" id="phraseProvenance" name="phraseProvenance" placeholder="成语出处">
        </div>
        <div class="form-group">
            <label for="phraseDemo">成语拼音</label>
            <input type="text" class="form-control" id="phraseDemo" name="phraseDemo" placeholder="成语示例">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
<!-- Latest compiled and minified JavaScript -->
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>
