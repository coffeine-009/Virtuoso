/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-05-06 22:00:00 :: 2014-05-07 23:32:08
 *
 * @address /Ukraine/Ivano-Frankivsk/Chornovola/114/51
 */
var Security = Security || {
    Model       : {},
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
        new Security.Controller.SecurityController();
        new User.SongController();

        Backbone.history.start();
    }
);
