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
            width:100%;
        }

        #bg-image-result {
            position: absolute;
            z-index: -1;
            margin-top: 0;
            height: 100%;
            width:100%;
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

        .transparent-no-border {
            background-color: transparent;
            border: none;
            outline: none;
        }

        #navbar-logo {
            margin-top: 5px;
        }

        #pixsearch-logo-upload {
            position: absolute;
            top: 25%;
            left: 50%;
            z-index:2;
            transform: translate(-50%, -50%);
        }

        #image-upload {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index:2;
            transform: translate(-50%, -50%);
        }

        #costum-dropzone {
            background-color: transparent;
            border: none;
            outline: none;
        }

        #upload-button {
            position: absolute;
            top: 70%;
            left: 50%;
            z-index:2;
            transform: translate(-50%, -50%);
        }

    </style>
</head>

<body>
    <nav role="navigation" class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <button id="navbar-logo" class="transparent-no-border">
                    <img src="<c:url value="/resources/img/index/navbar_logo.png"/>"/>
                </button>
            </div>
            <!-- Collection of nav links and other content for toggling -->
            <div id="navbarCollapse" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#slides" class="scroll-to-search">Search</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="start">
        <img id="bg-image-landing" src="<c:url value="/resources/img/index/Startseite_BG.gif"/>" class="img-responsive"/>
        <img id="pixsearch-logo" src="<c:url value="/resources/img/index/Startseite_Logo.png"/>"/>
        <a id="pixsearch-arrow" class="scroll-to-search" href="#slides">
            <img src="<c:url value="/resources/img/index/Startseite_Pfeil.png"/>" />
        </a>
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
        <form action="<c:url value="/upload"/>" class="dropzone transparent-no-border" id="image-upload" enctype="multipart/form-data" method="post">
            <div class="dz-default dz-message file-dropzone text-center well col-sm-12" id="costum-dropzone" >
                <img id="pixsearch-image-upload" src="<c:url value="/resources/img/upload/Seite2_Dropzone_Status1.png"/>">
                <div class="dropzone-previews"></div>
            </div>
        </form>

        <button id="upload-button" class="transparent-no-border">
            <span>
                <img src="<c:url value="/resources/img/upload/Seite2_ButtonGo_Status1.png"/>">
            </span>
        </button>
    </div>

    <div id="result">
        <img id="bg-image-result" src="<c:url value="/resources/img/index/Startseite_BG.gif"/>" class="img-responsive"/>
        <div class="container">
            <div class="row">
                <div class="col-xs-6" id="most-similar-0"></div>
                <div class="col-xs-6">
                        <div class="row">
                            <div class="col-xs-4">small</div>
                            <div class="col-xs-4">small</div>
                            <div class="col-xs-4">small</div>
                        </div>
                    <div class="row">
                        <div class="col-xs-4">small</div>
                        <div class="col-xs-4">small</div>
                        <div class="col-xs-4">small</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">small</div>
                        <div class="col-xs-4">small</div>
                        <div class="col-xs-4">small</div>
                    </div>
                </div>
           </div>
        </div>
    </div>
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

   $('#navbar-logo').on("click", function() {
        $('html, body').animate({
            scrollTop: $('#start').offset().top
        }, 1000);
   });

    $(".scroll-to-search").on('click', function(e) {

        // prevent default anchor click behavior
        e.preventDefault();

        // store hash
        var hash = this.hash;

        // animate
        $('html, body').animate({
            scrollTop: $(hash).offset().top
        }, 800, function(){

            // when done, add hash to url
            // (default click behaviour)
            window.location.hash = hash;
        });

    });

</script>
</html>
