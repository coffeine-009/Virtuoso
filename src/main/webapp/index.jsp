<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd"
>

<html lang = "en" data-framework = "backbone.js">

    <head>

        <meta charset = "UTF-8" />
        <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1">

        <meta name = "description" content = "">
        <meta name = "author" content = "">
        <meta name = "viewport" content = "width=device-width, initial-scale=1.0">

        <link rel = "icon" href = "/favicon.ico">

        <!-- Style -->
        <!-- Bootstrap -->
        <link
            rel = "stylesheet"
            href = "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
        >

        <link
            rel = "stylesheet"
            href = "//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css"
        >

        <link
            rel = "stylesheet"
            href = "/resources/client/module/main/view/style/main.css"
        >
        <link
            rel = "stylesheet"
            href = "/resources/client/module/user/view/style/main.css"
        >
        <link
            rel = "stylesheet"
            href = "/resources/client/module/user/view/style/song/create.css"
        >


        <!-- Scripts -->
        <!-- jQuery -->
        <script
            type = "text/javascript"
            src = "//code.jquery.com/jquery-1.10.2.min.js"
        ></script>
        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

        <!-- Bootstrap -->
        <script
            src = "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"
        ></script>

        <!-- Virtuoso -->
        <script
            type = "text/javascript"
            data-main = "/resources/client/module/Bootstrap.js"
            src = "/resources/client/library/require.js"
        ></script>


        <title>Virtuoso portal</title>

    </head>

    <body>

        <header
            class = "navbar navbar-default hero"
            role = "banner"
        >

            <div class = "">
                <div class = "navbar-header">
                    <button
                        class = "navbar-toggle"
                        type = "button"
                        data-toggle = "collapse"
                        data-target = ".bs-navbar-collapse"
                    >
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href = "/" class = "navbar-brand">VIRTUOSO</a>
                </div>

                <nav
                    class = "collapse navbar-collapse bs-navbar-collapse"
                    role = "navigation"
                >
                    <ul class = "nav navbar-nav navbar-left">
                        <li class = "dropdown">
                            <a
                                class = "dropdown-toggle"
                                href = "#"
                                data-toggle = "dropdown"
                            >
                                Song <b class="caret"></b>
                            </a>
                            <ul class = "dropdown-menu" role = "menu">
                                <li><a href = "#user/songs">Song</a></li>
                                <li><a href = "#user/song/create">Create</a></li>
                            </ul>
                        </li>
                    </ul>

                    <!-- Profile -->
                    <ul id = "security" class = "nav navbar-nav navbar-right">
                        <li>
                            <a href = "/#security/signup">Sign Up</a>
                        </li>
                        <li>
                            <a href = "/#security/signin">Sign In</a>
                        </li>
                    </ul>
                </nav>
            </div>

        </header>

        <!-- Breadcrumbs -->
        <div>
            <ul class="breadcrumb" >
                <li><a href="#">Home</a></li>
                <li><a href="#">Library</a></li>
                <li class="active">Data</li>
            </ul>
        </div>

        <!-- Main block -->
        <div id = "main" class = "main1 row1">

            <div id = "main-content" class = "content" >
                OK<br/>OK<br/>OK<br/>OK<br/>OK<br/>
                OK<br/>OK<br/>OK<br/>OK<br/>OK<br/>
            </div>

        </div>

        <footer>

            <div>Facebook | Twitter | Google+</div>
            <div>&copy 2014 by Coffeine</div>

        </footer>


        <!-- Message container -->
        <div id = "message-container"></div>

        <textarea id="blah" style="display:none;">tabstave notation=true
            staffs 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3</textarea>
    </body>

</html>
