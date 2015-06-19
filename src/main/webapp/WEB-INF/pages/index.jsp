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
    <%--Dropzone CSS--%>
    <link rel="stylesheet" value="/resources/libs/dropzone/dropzone.css"/>
</head>

<body>

    <form action="<c:url value="/upload"/>" class="dropzone" id="image-upload" enctype="multipart/form-data" method="post">
        <div class="dz-default dz-message file-dropzone text-center well col-sm-12">
            <span class="glyphicon glyphicon-paperclip"></span>
            <span>To attach files, drag and drop here
              <div class="dropzone-previews"></div>
            </span><br>
            <span>OR</span><br>
            <span>Just Click</span>
        </div>
    </form>

    <button id="upload-button" class="btn btn-primary">
        <span class="glyphicon glyphicon-upload">
            Upload
        </span>
    </button>

    <div id ="result"/>
</body>

<!-- jQuery -->
<script src="<c:url value="/resources/libs/jquery/jquery.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/libs/bootstrap3/bootstrap.min.js"/>"></script>

<!-- paralax -->
<script src="<c:url value="/resources/libs/jquery/jquery.stellar.js"/>"></script>

<!-- Dropzone.js -->
<script src="<c:url value="/resources/libs/dropzone/dropzone.js"/>"></script>

<script type="text/javascript" src='<c:url value="/resources/libs/bootstrap-dialog/bootstrap-dialog.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/app.js"/>'></script>

</html>
