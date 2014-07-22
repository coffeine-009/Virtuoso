<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang = "en" data-framework = "backbone.js">

    <head>

        <meta charset="UTF-8" />
        <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1">

        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="icon" href="favicon.ico">

        <!-- Style -->
        <!-- Bootstrap -->
        <link
            rel = "stylesheet"
            href = "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
        >
        <%--<link rel = "stylesheet" href = "<c:url value = "/resources/client/library/bootstrap/dist/css/bootstrap.css" />">--%>
        <%--<link rel = "stylesheet" href = "<c:url value = "/resources/client/library/bootstrap/dist/css/bootstrap-theme.css" />">--%>

        <%--<link rel="stylesheet" href="<c:url value = "/resources/client/module/user/view/style/main.css" />">--%>

        <!-- Scripts -->
        <!-- Scripts :: Libraries -->
        <!-- Scripts :: Libraries :: jQuery -->
        <script
            type = "text/javascript"
            src = "//code.jquery.com/jquery-1.10.2.min.js"
        ></script>
        <%--<script type = "text/javascript" src = "/resources/client/library/jquery/dist/jquery.min.js"></script>--%>

        <!-- Bootstrap -->
        <script
            src = "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"
        ></script>
        <%--<script type = "javascript" src = "/resources/client/library/bootstrap/dist/js/bootstrap.min.js"></script>--%>

        <!-- Virtuoso -->
        <script
            type = "text/javascript"
            data-main = "/resources/client/module/bootstrap.js"
            src = "/resources/client/library/require.js"
        ></script>


        <title>Virtuoso portal</title>

    </head>

    <body>

        <header
            class = "navbar navbar-default hero"
            role = "banner"
        >

            <div class = "container">
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
                    <a href = "/" class = "navbar-brand">Virtuoso</a>
                </div>

                <nav
                    class = "collapse navbar-collapse bs-navbar-collapse"
                    role = "navigation"
                >
                    <ul class = "nav navbar-nav navbar-right">
                        <li class = "dropdown">
                            <a
                                class = "dropdown-toggle"
                                href = "#"
                                data-toggle = "dropdown"
                            >
                                Songs <b class="caret"></b>
                            </a>
                            <ul class = "dropdown-menu" role = "menu">
                                <li><a href = "#user/songs">Songs</a></li>
                                <li><a href = "index2.html">Home 2 </a></li>
                                <li><a href = "index3.html">Home 3 </a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                Showcase <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="features.html">Features</a></li>
                                <li><a href="services.html">Services</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                Pricing <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="pricing.html">Pricing charts</a></li>
                                <li><a href="charts.html">Comparison tables</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                More pages <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="aboutus.html">About us</a></li>
                                <li><a href="contactus.html">Contact us</a></li>
                                <li><a href="gallery.html">Gallery</a></li>
                                <li><a href="portfolio.html">Portfolio</a></li>
                                <li><a href="portfolio-item.html">Portfolio Item</a></li>
                                <li><a href="invoice.html">Invoice page</a></li>
                                <li><a href="timeline.html">Timeline Page</a></li>
                                <li><a href="docs.html">API Documentation</a></li>
                                <li><a href="support.html">Support</a></li>
                                <li><a href="coming-soon.html">Coming Soon</a></li>
                                <li><a href="status.html">Status</a></li>
                                <li><a href="signup.html">Sign Up & Sign In</a></li>
                                <li><a href="signup-rotate.html">Sign Up Miscellaneous</a></li>
                                <li><a href="404.html">404 Page</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                Blog <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="blog.html">Blog</a></li>
                                <li><a href="blogpost.html">Blog Post</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>

        </header>

        <div id = "main-content">

        </div>



        <textarea id="blah" style="display:none;">tabstave notation=true
            notes 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3</textarea>
    </body>

</html>
