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
        <link rel = "stylesheet" href = "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <%--<link rel = "stylesheet" href = "<c:url value = "/resources/client/library/bootstrap/dist/css/bootstrap.css" />">--%>
        <%--<link rel = "stylesheet" href = "<c:url value = "/resources/client/library/bootstrap/dist/css/bootstrap-theme.css" />">--%>

        <%--<link rel="stylesheet" href="<c:url value = "/resources/client/module/user/view/style/main.css" />">--%>

        <!-- Scripts -->
        <!-- stylesheets -->
        <link rel="stylesheet" type="text/css" href="/resources/client/library/theme.css">
        <link rel="stylesheet" type="text/css" href="/resources/client/library/animate.css">

        <!-- Libraries -->
        <!-- jQuery -->
        <script type = "text/javascript" src = "/resources/client/library/jquery/dist/jquery.min.js"></script>

        <!-- Bootstrap -->
        <script src = "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <%--<script type = "javascript" src = "/resources/client/library/bootstrap/dist/js/bootstrap.min.js"></script>--%>

        <!-- Virtuoso -->
        <script
            type = "text/javascript"
            data-main = "/resources/client/module/bootstrap.js"
            src = "/resources/client/library/require.js"
        ></script>

        <script src="/resources/client/library/theme.js"></script>


        <title>Virtuoso portal</title>

    </head>

    <body id = "home">

        <header class="navbar navbar-inverse hero" role="banner">
            <div class="container">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="index.html" class="navbar-brand">Virtuoso</a>
                </div>
                <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                Home alts <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#user/songs">Songs</a></li>
                                <li><a href="index2.html">Home 2 (Slider)</a></li>
                                <li><a href="index3.html">Home 3 (Off-canvas menu)</a></li>
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

        <div id="hero">
            <div class="container">
                <h1 class="hero-text animated fadeInDown">
                    The best interfaces <br />
                    for your web & mobile apps
                </h1>
                <p class="sub-text animated fadeInDown">
                    Get a complete existing website with great design up and running in no time.
                </p>
                <div class="cta animated fadeInDown">
                    <a href="features.html" class="button-outline">See the tour</a>
                    <a href="signup.html" class="button">Sign up free</a>
                </div>
                <div class="img"></div>
            </div>
        </div>

        <div id="features">
            <div class="container">
                <div class="row header">
                    <div class="col-md-12">
                        <h2>Need an easy way to customize your site?</h2>
                        <p>React is perfect for novice developers and experts alike.</p>
                    </div>
                </div>
                <div class="row feature">
                    <div class="col-md-6 info">
                        <h4>You don't need to have great technical experience to use your product.</h4>
                        <p>
                            Whether you want to fill this paragraph with some text like I'm doing right now, this place
                            is perfect to describe some features or anything you want - React has a complete solution for you.
                        </p>
                    </div>
                    <div class="col-md-6 image">
                        <img src="/resources/images/feature1.png" class="img-responsive" alt="feature1" />
                    </div>
                </div>
                <div class="divider"></div>
                <div class="row feature backwards">
                    <div class="col-md-6 info">
                        <h4>A fully featured well design template that works.</h4>
                        <p>
                            You have complete control over the look & feel of your website, we offer the best quality so you
                            take your site up and running in no time.
                        </p>
                        <p>
                            Write some text here to explain the features of your site or application, it
                            has lots of uses.
                        </p>
                    </div>
                    <div class="col-md-6 image">
                        <img src="/resources/images/feature2.png" class="img-responsive" alt="feature2" />
                    </div>
                </div>
            </div>
        </div>

        <div id="pricing">
            <div class="container">
                <div class="row header">
                    <div class="col-md-12">
                        <h3>Free trial. No contract. Cancel when you want.</h3>
                        <p>All plans include a 7-day free trial</p>
                    </div>
                </div>
                <div class="row charts">
                    <div class="col-md-4">
                        <div class="chart first">
                            <div class="quantity">
                                <span class="dollar">$</span>
                                <span class="price">29</span>
                                <span class="period">/month</span>
                            </div>
                            <div class="plan-name">Freelance</div>
                            <div class="specs">
                                <div class="spec">
                                    <span class="variable">5</span>
                                    team members
                                </div>
                                <div class="spec">
                                    <span class="variable">Digital</span>
                                    recurring billing
                                </div>
                                <div class="spec">
                                    <span class="variable">Virtual</span>
                                    online terminal
                                </div>
                                <div class="spec">
                                    <span class="variable">10</span>
                                    total products
                                </div>
                                <div class="spec">
                                    <span class="variable">1.0%</span>
                                    Transaction fee
                                </div>
                            </div>
                            <a class="btn-signup button-clear" href="signup.html">
                                <span>Start free trial</span>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="chart featured">
                            <div class="popular">Most popular</div>
                            <div class="quantity">
                                <span class="dollar">$</span>
                                <span class="price">79</span>
                                <span class="period">/month</span>
                            </div>
                            <div class="plan-name">Profesional</div>
                            <div class="specs">
                                <div class="spec">
                                    <span class="variable">15</span>
                                    team members
                                </div>
                                <div class="spec">
                                    <span class="variable">Digital</span>
                                    recurring billing
                                </div>
                                <div class="spec">
                                    <span class="variable">Virtual</span>
                                    online terminal
                                </div>
                                <div class="spec">
                                    <span class="variable">15</span>
                                    total products
                                </div>
                                <div class="spec">
                                    <span class="variable">0.5%</span>
                                    Transaction fee
                                </div>
                            </div>
                            <a class="btn-signup button-clear" href="signup.html">
                                <span>Start free trial</span>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="chart last">
                            <div class="quantity">
                                <span class="dollar">$</span>
                                <span class="price">119</span>
                                <span class="period">/month</span>
                            </div>
                            <div class="plan-name">Premium</div>
                            <div class="specs">
                                <div class="spec">
                                    <span class="variable">Unlimited</span>
                                    team members
                                </div>
                                <div class="spec">
                                    <span class="variable">Digital</span>
                                    recurring billing
                                </div>
                                <div class="spec">
                                    <span class="variable">Virtual</span>
                                    online terminal
                                </div>
                                <div class="spec">
                                    <span class="variable">25</span>
                                    total products
                                </div>
                                <div class="spec">
                                    <span class="variable">No</span>
                                    Transaction fee
                                </div>
                            </div>
                            <a class="btn-signup button-clear" href="signup.html">
                                <span>Start free trial</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="slider">
            <div class="container">
                <div class="row header">
                    <div class="col-md-12">
                        <h3>Includes all pages that a complete theme should have</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 slide-wrapper">
                        <div class="slideshow">
                            <div class="btn-nav prev"></div>
                            <div class="btn-nav next"></div>
                            <div class="slide active">
                                <img src="/resources/images/slider/slide3.png" alt="slide3" />
                            </div>
                            <div class="slide">
                                <img src="/resources/images/slider/slide4.png" alt="slide4" />
                            </div>
                            <div class="slide">
                                <img src="/resources/images/slider/slide1.png" alt="slide1" />
                            </div>
                            <div class="slide">
                                <img src="/resources/images/slider/slide5.png" alt="slide5" />
                            </div>
                            <div class="slide">
                                <img src="/resources/images/slider/slide2.png" alt="slide2" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="testimonials">
            <div class="container">
                <div class="row header">
                    <div class="col-md-12">
                        <h3>Trusted by a lot businesses around the world:</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="testimonial pull-right">
                            <div class="quote">
                                I am just quoting some stuff but I am seriously happy about this product. Has a lot of powerful
                                features and is so easy to set up, I could stay customizing it day and night, it's just so much fun!
                                <div class="arrow-down">
                                    <div class="arrow"></div>
                                    <div class="arrow-border"></div>
                                </div>
                            </div>
                            <div class="author">
                                <img src="/resources/images/testimonials/testimonial1.jpg" class="pic" alt="testimonial1" />
                                <div class="name">John McClane</div>
                                <div class="company">Microsoft</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="testimonial">
                            <div class="quote">
                                This thing is one of those tools that everybody should be using. I really like it and with this ease to use, you can kickstart your projects and apps and just focus on your business!
                                <div class="arrow-down">
                                    <div class="arrow"></div>
                                    <div class="arrow-border"></div>
                                </div>
                            </div>
                            <div class="author">
                                <img src="/resources/images/testimonials/testimonial2.jpg" class="pic" alt="testimonial2" />
                                <div class="name">Karen Jones</div>
                                <div class="company">Pixar Co.</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="cta">
            <p>
                Start your free 14 day trial!
            </p>
            <a href="signup.html">
                Sign up for free
            </a>
        </div>

        <div id="clients">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h3>Our Customers</h3>
                        <p>
                            These are some of our customers who have helped us by using our product.
                        </p>
                        <div class="logos">
                            <img src="/resources/images/logos/google.png">
                            <img src="/resources/images/logos/facebook.png">
                            <img src="/resources/images/logos/apple.png">
                            <img src="/resources/images/logos/stripe.png">
                            <img src="/resources/images/logos/yahoo.png">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="footer-white">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3 menu">
                        <h3>Overview</h3>
                        <ul>
                            <li>
                                <a href="features.html">Features</a>
                            </li>
                            <li>
                                <a href="services.html">Services</a>
                            </li>
                            <li>
                                <a href="pricing.html">Pricing</a>
                            </li>
                            <li>
                                <a href="support.html">Support</a>
                            </li>
                            <li>
                                <a href="blog.html">Blog</a>
                            </li>
                            <li>
                                <a href="blog.html">Coming soon</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-sm-3 menu">
                        <h3>Menu</h3>
                        <ul>
                            <li>
                                <a href="features.html">About us</a>
                            </li>
                            <li>
                                <a href="services.html">Contact us</a>
                            </li>
                            <li>
                                <a href="aboutus.html">Jobs</a>
                                <a href="aboutus.html" class="hiring">
                                    We're hiring!
                                </a>
                            </li>
                            <li>
                                <a href="support.html">Portfolio</a>
                            </li>
                            <li>
                                <a href="blog.html">Status</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-sm-2 menu">
                        <h3>Social</h3>
                        <ul>
                            <li>
                                <a href="features.html">Youtube</a>
                            </li>
                            <li>
                                <a href="services.html">Facebook</a>
                            </li>
                            <li>
                                <a href="pricing.html">Twitter</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-sm-4 newsletter">
                        <div class="signup clearfix">
                            <p>
                                Sign up for the newsletter and we'll inform you of updates, offers and more.
                            </p>
                            <form>
                                <input type="text" name="email" class="form-control" placeholder="Your email address" />
                                <input type="submit" value="Sign up" />
                            </form>
                        </div>
                        <a href="#">
                            <img src="/resources/images/social/social-tw.png" alt="twitter" />
                        </a>
                        <a href="#">
                            <img src="/resources/images/social/social-dbl.png" alt="dribbble" />
                        </a>
                    </div>
                </div>
                <div class="row credits">
                    <div class="col-md-12">
                        Copyright © 2014. React
                    </div>
                </div>
            </div>
        </div>



        <textarea id="blah" style="display:none;">tabstave notation=true
            notes 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3 10/4 4-5-6/3</textarea>
    </body>

</html>
