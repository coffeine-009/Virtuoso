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
        <%--<link rel="stylesheet" href = "//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">--%>

        <link rel="stylesheet" href="<c:url value = "/resources/client/module/user/view/style/main.css" />">

        <script type = "text/javascript" data-main = "/resources/client/module/user/bootstrap.js" src = "/resources/client/library/require.js"></script>
        <script type = "text/javascript" src = "/resources/client/library/Coffeine/NotesEditor/bootstrap.js"></script>
        <script type = "text/javascript" src = "/resources/client/library/Coffeine/NotesEditor/view/ViewerView.js"></script>
        <script type = "text/javascript" src = "/resources/client/library/Coffeine/NotesEditor/view/helper/StaffHelper.js"></script>
        <script type = "text/javascript" src = "/resources/client/library/Coffeine/NotesEditor/controller/ViewerController.js"></script>
        <!-- Libraries -->
        <script src = "//code.jquery.com/jquery-1.10.2.min.js" type = "text/javascript"></script>
        <%--<script type = "text/javascript" src = "//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>--%>
        <script src = "http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.3.3/underscore.js" type = "text/javascript"></script>
        <%--<script src = "http://cdnjs.cloudflare.com/ajax/libs/backbone.js/0.9.2/backbone-min.js" type = "text/javascript"></script>--%>
        <!--<script src = "http://cdnjs.cloudflare.com/ajax/libs/backbone-localstorage.js/1.0/backbone.localStorage-min.js" type = "text/javascript"></script>-->

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
                </ul>
            </nav>
        </header>

        <div class = "main">

            <nav class = "menu-left">
                Operations
                <ul>
                    <li>Create</li>
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



        <h1>VexTab Playground</h1>

        <div style="float:left;width:600;">
            <canvas id="boo"></canvas>
            <div id="error"></div>
        </div>

        <div style="float:left;">
            <textarea id="blah">options space=20 tab-stems=true stave-distance=40 tab-stem-direction=down
                tabstave notation=true key=A time=4/4
                notes :q =|: (5/2.5/3.7/4) :8 7-5h6/3 ^3^ 5h6-7/5 ^3^ :q 7V/4 |
                notes :8 t12p7/4 s5s3/4 :8 3s:16:5-7/5 :h p5/4
                text :w, |#segno, ,|, :hd, , #tr


                options space=65
                tabstave notation=true
                notes :q (5/4.5/5) (7/4.7/5)s(5/4.5/5) ^3^
                notes :8 7-5/4 $.a./b.$ (5/4.5/5)h(7/5) =:|
                notes :8 (12/5.12/4)ds(5/5.5/4)u 3b4/5
                notes :h (5V/6.5/4.6/3.7/2) $.italic.let ring$ =|=
                text :h, ,.font=Times-12-italic, D.S. al coda, |#coda
                text :h, ,.-1, .font=Arial-14-bold,A13
                text ++, .30, #f

                options space=70</textarea>
        </div>
    </body>

</html>
