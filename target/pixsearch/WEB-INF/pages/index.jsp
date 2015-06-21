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
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/libs/bootstrap-dialog/bootstrap-dialog.min.css"/>'>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/style.css"/>'/>
    <%--Dropzone CSS--%>
    <link rel="stylesheet" href="<c:url value="/resources/libs/dropzone/dropzone.css"/>"/>
    <%--Superslides--%>
    <link rel="stylesheet" href="<c:url value="/resources/css/superslides.css"/>"/>
    <style type="text/css">
        #bg-image-landing {
            position: relative;
            z-index: -1;
            margin-top: 0;
            height: 100%;
        }

        #pixsearch-logo{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        #pixsearch-arrow {
            position: absolute;
            top: 90%;
            left: 50%;
        }

        #pixsearch-logo-upload {
            position: absolute;
            top: 25%;
            left: 50%;
            z-index:2;
            transform: translate(-50%, -50%);
        }

        #pixsearch-dropzone {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index:2;
            transform: translate(-50%, -50%);
        }

        #pixsearch-dropzone-go {
            position: absolute;
            top: 70%;
            left: 50%;
            z-index:2;
            transform: translate(-50%, -50%);
        }
    </style>
</head>

<body>
    <div id="start">
        <img id="bg-image-landing" src="<c:url value="/resources/img/index/Startseite_BG.gif"/>" class="img-responsive"/>
        <img id="pixsearch-logo" src="<c:url value="/resources/img/index/Startseite_Logo.png"/>"/>
        <img id="pixsearch-arrow" src="<c:url value="/resources/img/index/Startseite_Pfeil.png"/>"/>
    </div>

    <div id="slides">
        <div class="slides-container">
            <img src="<c:url value="/resources/img/slider/Seite2_BG.png"/>"/>
            <img src="<c:url value="/resources/img/slider/Seite2_BG2.png"/>"/>
            <img src="<c:url value="/resources/img/slider/Seite2_BG3.png"/>"/>
            <img src="<c:url value="/resources/img/slider/Seite2_BG4.png"/>"/>
            <img src="<c:url value="/resources/img/slider/Seite2_BG5.png"/>"/>
            <img src="<c:url value="/resources/img/slider/Seite2_BG6.png"/>"/>
        </div>
        <img id="pixsearch-logo-upload" src="<c:url value="/resources/img/upload/Seite2_Logo.png"/>">
        <img id="pixsearch-dropzone" src="<c:url value="/resources/img/upload/Seite2_Dropzone_Status1.png"/>">
        <img id="pixsearch-dropzone-go" src="<c:url value="/resources/img/upload/Seite2_ButtonGo_Status1.png"/>">

    </div>

    <%--<button id="upload-button" class="btn btn-primary">--%>
        <%--<span class="glyphicon glyphicon-upload">--%>
            <%--Upload--%>
        <%--</span>--%>
    <%--</button>--%>

    <%--<div id ="result"/>--%>
</body>

<!-- jQuery -->
<script src="<c:url value="/resources/libs/jquery/jquery.js"/>"></script>
<script src="<c:url value="/resources/libs/jquery/jquery.animate-enhanced.min.js"/>"></script>
<script src="<c:url value="/resources/libs/jquery/jquery.easing.1.3.js"/>"></script>
<%--Superslides--%>
<script src="<c:url value="/resources/libs/jquery/jquery.superslides.js"/>" type="text/javascript" charset="utf-8"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/libs/bootstrap3/bootstrap.min.js"/>"></script>
<!-- paralax -->
<script src="<c:url value="/resources/libs/jquery/jquery.stellar.js"/>"></script>
<!-- Dropzone.js -->
<script src="<c:url value="/resources/libs/dropzone/dropzone.js"/>"></script>
<script type="text/javascript" src='<c:url value="/resources/libs/bootstrap-dialog/bootstrap-dialog.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/app.js"/>'></script>
<script>
    $('#slides').superslides({
        animation: 'fade',
        play: 8000
    });
</script>
</html>
