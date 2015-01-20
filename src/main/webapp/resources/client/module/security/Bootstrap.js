/*
 * @copyright (c) 2015, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 1/9/15 10:01 PM :: ../../.. ..:.. ..
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

var Coffeine = Coffeine || {
    Security: {
        OAuth: {
            _2: {
                Provider : null
            }
        }
    }
};

/**
 * Module: security
 *
 * @version 1.0
 */
define(
    [
        "jquery",
        "underscore",
        "backbone",
        "backboneoauth",
        'oauth2'
    ],
    function(
        $,
        _,
        Backbone,
        BackboneOAuth,
        OAuth
    ) {
        // Include dependencies of module
        requirejs(
            [
                //- Security -//
                "/resources/client/module/security/model/Authorization.js",
                "/resources/client/module/security/model/SignUp.js",
                "/resources/client/module/security/view/script/security/SignInView.js",
                "/resources/client/module/security/view/script/security/SignUpView.js",
                "/resources/client/module/security/controller/SecurityController.js"
            ],
            function (
                //- Security -//
                AuthorizationModel,
                SignInView,
                RegistrationView,
                SecurityController
            ) {
                // Init security
                Security.Model.OAuth2 = new Backbone.OAuth2(
                    new Coffeine.Security.OAuth._2.Provider(
                        'http://localhost:8080/oauth/token',
                        'developer',
                        'developer32'
                    )
                );

                new Security.Controller.SecurityController();

                $(document).ready(function() {
                    // Set security interceptors
                    Security.Model.OAuth2.setControllers(
                        [
                            User.Controller.user
                        ],
                        function() {
                            var view = new Security.View.SignInView();

                            view.signinSuccess();
                        }
                    );
                });

                // Init application
                Backbone.history.start();
            }
        );
    }
);
