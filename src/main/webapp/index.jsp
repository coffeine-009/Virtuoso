<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang = "en" data-framework = "backbone.js">

    <head>

        <meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
        <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1">

        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="favicon.ico">

        <!-- Plugins -->
        <%--<link rel="stylesheet" href = "//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">--%>

        <!-- Bootstrap -->
        <link rel = "stylesheet" href = "<c:url value = "resources/client/library/bootstrap/dist/css/bootstrap.css" />">
        <link rel = "stylesheet" href = "<c:url value = "resources/client/library/bootstrap/dist/css/bootstrap-theme.css" />">

        <link rel="stylesheet" href="<c:url value = "/resources/client/module/user/view/style/main.css" />">

        <%--<script type = "text/javascript" src = "/resources/client/library/Coffeine/NotesEditor/bootstrap.js"></script>--%>
    <%--<script type = "text/javascript" src = "/resources/client/library/Coffeine/NotesEditor/view/ViewerView.js"></script>--%>
    <%--<script type = "text/javascript" src = "/resources/client/library/Coffeine/NotesEditor/view/helper/StaffHelper.js"></script>--%>
    <%--<script type = "text/javascript" src = "/resources/client/library/Coffeine/NotesEditor/controller/ViewerController.js"></script>--%>
        <!-- Libraries -->
        <script src = "//code.jquery.com/jquery-1.10.2.min.js" type = "text/javascript"></script>
    <%--<script type = "text/javascript" src = "//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>--%>
    <%--<script src = "http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.3.3/underscore.js" type = "text/javascript"></script>--%>
    <%--<script src = "http://cdnjs.cloudflare.com/ajax/libs/backbone.js/0.9.2/backbone-min.js" type = "text/javascript"></script>--%>
        <!--<script src = "http://cdnjs.cloudflare.com/ajax/libs/backbone-localstorage.js/1.0/backbone.localStorage-min.js" type = "text/javascript"></script>-->
        <%--<script src = "/resources/client/library/tabdiv-min.js" type = "text/javascript"></script>--%>
        <%--<script src = "/resources/client/library/vexflow-min.js" type = "text/javascript"></script>--%>

        <!-- Bootstrap -->
        <script type = "javascript" src = "resources/client/library/bootstrap/dist/js/bootstrap.js"></script>

        <script type = "text/javascript" data-main = "/resources/client/module/bootstrap.js" src = "/resources/client/library/require.js"></script>

        <title>Virtuoso portal</title>

    </head>

    <body>

        <header class="navbar navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Virtuoso</a>
                </div>

                <nav class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href = "#user/songs">Songs</a>
                        </li>
                    </ul>
                    <div class = "security navbar-right">
                        <a href = "#security/signin">Sign In</a> |
                        <a href = "#security/registration">Registration</a>
                    </div>
                </nav>
            </div>
        </header>

        <div class = "main container">

            <nav class = "menu-left">
                <ul>
                    <li><a href = "#user/song/create">Create</a></li>
                    <li>Load</li>
                    <li>Edit</li>
                    <li>Delete</li>
                </ul>
            </nav>

            <div id = "main-content" class = "content">
                
            </div>

        </div>

        <footer>
            <div>&copy; 2014 by Coffeine</div>
            <div>About</div>
        </footer>

        <textarea id="blah" style="display:none;">tabstave notation=true
            notes 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3</textarea>
    </body>

</html>
