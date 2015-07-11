<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Full Slider - Start Bootstrap Template</title>
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/libs/bootstrap3/css/bootstrap.min.css"/>" rel="stylesheet">
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


        #upload-button-img {
            background-image: url(/resources/img/upload/Seite2_ButtonGo_Status1.png);
        }

        #upload-button-img:hover {
            background-image: url(/resources/img/upload/Seite2_ButtonGo_StatusClick.png);
        }

        #pixsearch-image-upload {
            background-image: url(/resources/img/upload/Seite2_Dropzone_Status1.png);
        }

        #pixsearch-image-upload:hover {
            background-image: url(/resources/img/upload/Seite2_Dropzone_StatusHoover.png);
        }

        .similar {
            position: relative;
            left: 50%;
            transform: translate(-50%);
        }

        .similar-header {
            position: relative;
            margin: 0;
            padding-top: 100px;
            padding-bottom: 50px;
            color: white;
        }

        #pixsearch-image-upload {
            position:relative;
        }

        #pixsearch-image-preview {
            position:relative;
        }

        #reload {
            position: absolute;
            left: 50%;
            margin-top: 50px;
            transform: translate(-50%);
        }

        .dz-preview {
            background: transparent !important;
        }

        #waiting {
            width: 1000px;
        }

        #waiting-text * {
            color: white;
        }

    </style>
</head>

<div>
    <nav role="navigation" class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
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
                <div id="pixsearch-image-preview" class="dropzone-previews"></div>
                <div id="waiting">
                    <h1 id="waiting-text"></h1>
                </div>
            </div>
        </form>

        <button type="button" data-toggle="modal" data-target="#waitingModal" id="upload-button" class="transparent-no-border">
            <span>
                <img id="upload-button-img" src="<c:url value="/resources/img/upload/Seite2_ButtonGo_Status1.png"/>">
            </span>
        </button>
    </div>

    <div id="result" class="hidden">
        <img id="bg-image-result" src="<c:url value="/resources/img/index/Startseite_BG.gif"/>" class="img-responsive"/>
        <h1 class="similar-header text-center">Most similar images</h1>

        <div id="carousel-result" class="carousel slide" data-interfal="false">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-result" data-slide-to="0" class="active"></li>
                <c:forEach begin="1" end="9" varStatus="i">
                    <li data-target="#carousel-result" data-slide-to="${i.index}"></li>
                </c:forEach>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img class="similar" id="most-similar-0">
                    <div class="carousel-caption">
                        #1 similar
                    </div>
                </div>
                <c:forEach begin="1" end="9" varStatus="i">
                    <div class="item">
                        <img class="similar" id="most-similar-${i.index}">
                        <div class="carousel-caption">
                             #${i.index +1} similar
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-result" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-result" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

        <button id="reload" type="button" class="btn btn-default btn-large center" onclick="location.href='<c:url value="/"/>'">
            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> New Search
        </button>


    </div>

    <%--<div class="modal fade" id="waitingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
        <%--<div class="modal-dialog" role="document">--%>
            <%--<div class="modal-content">--%>
                <%--<div class="modal-header">--%>
                    <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                    <%--<h4 class="modal-title" id="myModalLabel">Modal title</h4>--%>
                <%--</div>--%>
               <%--<div class="modal-body">--%>
                   <%--<span id="spinner" class="glyphicon glyphicon-refresh" aria-hidden="true"></span>--%>
                   <%--waiting for result--%>
               <%--</div>--%>

                <%--<div class="modal-footer">--%>
                    <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                    <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>

</body>
<!-- jQuery -->
<script src="<c:url value="/resources/libs/jquery/jquery.js"/>"></script>
<script src="<c:url value="/resources/libs/jquery/jquery.animate-enhanced.min.js"/>"></script>
<script src="<c:url value="/resources/libs/jquery/jquery.easing.1.3.js"/>"></script>
<%--Superslides--%>
<script src="<c:url value="/resources/libs/jquery/jquery.superslides.js"/>" type="text/javascript" charset="utf-8"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/libs/bootstrap3/js/bootstrap.min.js"/>"></script>
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
