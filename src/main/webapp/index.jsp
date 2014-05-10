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

        <!-- Plugins -->
        <link rel="stylesheet" href = "//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">

        <link rel="stylesheet" href="<c:url value = "/resources/client/module/user/view/style/main.css" />">

        <!-- Libraries -->
        <script src = "http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type = "text/javascript"></script>
        <script src = "http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.3.3/underscore-min.js" type = "text/javascript"></script>
        <script src = "http://cdnjs.cloudflare.com/ajax/libs/backbone.js/0.9.2/backbone-min.js" type = "text/javascript"></script>
        <!--<script src = "http://cdnjs.cloudflare.com/ajax/libs/backbone-localstorage.js/1.0/backbone.localStorage-min.js" type = "text/javascript"></script>-->

	<script type = "text/javascript" sr = "//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>

        <script src = "<c:url value = "/resources/client/module/user/model/Song.js" />" type = "text/javascript"></script>
        <script src = "<c:url value = "/resources/client/module/user/model/Songs.js" />" type = "text/javascript"></script>
        <script src = "<c:url value = "/resources/client/module/user/view/script/SongView.js" />" type = "text/javascript"></script>
        <script src = "<c:url value = "/resources/client/module/user/view/script/SongsView.js" />" type = "text/javascript"></script>
        <script src = "<c:url value = "/resources/client/module/user/controller/SongController.js" />" type = "text/javascript"></script>
        <script src = "<c:url value = "/resources/client/module/user/bootstrap.js" />" type = "text/javascript"></script>

        <script id="song" type="text/template">
            Content:
            <input id="test" type="button" value="ok" />
        </script>

        <title>Virtuoso portal</title>

    </head>

    <body>

        <header>
            <div class = "logo">
                <a href = "#user/songs">
                    <span>Virtuoso</span>
                </a>
            </div>
            <nav>
                <ul>
                    <li>
                        <a href = "#user/songs">Songs</a>
                    </li>
                    <li>
                        <a href = "#user/song/">Song</a>
                    </li>
                </ul>
            </nav>
        </header>

        <div class = "main">

            <nav class = "menu-left">
                Operations
                <ul>
                    <li>Create</li>
<!--                    <li>Load</li>
                    <li>Edit</li>
                    <li>Delete</li>-->
                </ul>
            </nav>

            <div id = "main-content" class = "content">
                
            </div>

        </div>

        <footer>&copy; 2014 by Coffeine</footer>

    </body>

</html>
