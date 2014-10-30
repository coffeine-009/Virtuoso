/*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
 */
var Security = Security || {
    Model       : {
        OAuth2      : {}
    },
    View        : {},
    Controller  : {}

};
var User = User || {};

requirejs.config(
    {
        baseUrl : "/resources/client/library",
        paths   : {
            templates       : "/resources/client/module/user/view/templates",
            tpl             : "/resources/client/library/tpl",
            text            : "/resources/client/library/text",
//            jquery      : "/resources/client/library/jquery",
            datatables      : "/resources/client/library/jquery.dataTables",
            underscore      : "/resources/client/library/underscore",
            backbone        : "/resources/client/library/backbone",
            backboneoauth   : "/resources/client/library/backbone.oauth2",
            vextab          : "/resources/client/library/vexflow-min",
            vextabdiv       : "/resources/client/library/tabdiv-min",
            user            : "/resources/client/module/user"
        },
        shim: {
//            jquery      : {
//                exports : "$"
//            },
            datatables  : {
                deps: [ "jquery" ]
            },
            underscore: {
                exports: '_'
            },
            backbone: {
                deps: ['underscore', 'jquery'],
                exports: 'Backbone'
            }
        }
    }
);

requirejs(
    [
        //- Security -//
        "/resources/client/module/security/model/Authorization.js",
        "/resources/client/module/security/model/SignUp.js",
        "/resources/client/module/security/view/script/security/SignInView.js",
        "/resources/client/module/security/view/script/security/SignUpView.js",
        "/resources/client/module/security/controller/SecurityController.js",
        //- Song -//
        "/resources/client/module/user/model/Song.js",
        "/resources/client/module/user/model/Songs.js",
        "/resources/client/module/user/view/script/song/CreateView.js",
        "/resources/client/module/user/view/script/song/SongView.js",
        "/resources/client/module/user/view/script/song/SongsView.js",
        "/resources/client/module/user/controller/SongController.js"
    ],
    function(
        //- Security -//
        AuthorizationModel,
        SignInView,
        RegistrationView,
        SecurityController,

        //- Song -//
        SongModel,
        SongsModel,
        SongView,
        SongsView,
        SongController
    ) {
        // Init security
        Security.Model.OAuth2 = new Backbone.OAuth2({
            clientId: "developer",
            clientSecret: "developer32"
        });

        new Security.Controller.SecurityController();
        new User.SongController();

        Backbone.history.start();
    }
);
