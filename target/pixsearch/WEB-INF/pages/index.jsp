<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Full Slider - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/libs/bootstrap3/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Bootstrap Dialog -->
    <link rel="stylesheet" type="text/css"
          href='<c:url value="/resources/libs/bootstrap-dialog/bootstrap-dialog.min.css"/>'>
    <link rel="stylesheet" type="text/css"
          href='<c:url value="/resources/css/style.css"/>'>
</head>

<body>

<form action="<c:url value="/upload"/>" class="dropzone" id="my-awesome-dropzone" enctype="multipart/form-data" method="post">
    <input type="file" name="file" />
    <input type="submit" class="btn btn-primary">upload</input>
</form>



</body>


<!-- jQuery -->
<script src="<c:url value="/resources/libs/jquery/jquery.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/libs/bootstrap3/bootstrap.min.js"/>"></script>

<!-- paralax -->
<script src="<c:url value="/resources/libs/jquery/jquery.stellar.js"/>"></script>

<!-- Dropzone.js -->
<script src="<c:url value="/resources/libs/dropzone/dropzone.js"/>"></script>


<script type="text/javascript"
        src='<c:url value="/resources/libs/bootstrap-dialog/bootstrap-dialog.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/app.js"/>'></script>

</body>

</html>
