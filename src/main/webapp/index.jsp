<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang = "en" data-framework = ""backbone.js>

    <head>

        <meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
        <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1">

        <!-- Libraries -->
        <script src = "<c:url value = "/resources/client/library/require.js" />" type = "text/javascript"></script>
        <script src = "http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type = "text/javascript"></script>
        <script src = "http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.3.3/underscore-min.js" type = "text/javascript"></script>
        <script src = "http://cdnjs.cloudflare.com/ajax/libs/backbone.js/0.9.2/backbone-min.js" type = "text/javascript"></script>
        <script src = "http://cdnjs.cloudflare.com/ajax/libs/backbone-localstorage.js/1.0/backbone.localStorage-min.js" type = "text/javascript"></script>

        <title>Virtuoso portal</title>

    </head>

    <body>

        <div id = "page">

            <header>Menu</header>

            <div class = "main">

                <div class = "menu-left">Left menu</div>

                <div class = "content">Content</div>

            </div>

            <footer>C</footer>

        </div>

    </body>

</html>
