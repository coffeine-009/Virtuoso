/*
 * @copyright (c) 2015, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 1/9/15 10:35 PM :: ../../.. ..:.. ..
 *
 * @address /Ukraine/Ivano-Frankivsk
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
            jquery          : "//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min",
            datatables      : "/resources/client/library/jquery.dataTables",
            underscore      : "/resources/client/library/underscore",
            backbone        : "/resources/client/library/backbone",
            oauth2          : "/resources/client/library/Coffeine/Security/OAuth/2.0/Security_OAuth_2.0",
            backboneoauth   : "/resources/client/library/Coffeine/Security/OAuth/2.0/Backbone_Security_OAuth_2.0",
            vextab          : "/resources/client/library/vexflow-min",
            vextabdiv       : "/resources/client/library/tabdiv-min",
            user            : "/resources/client/module/user"
        },
        map: {
            // '*' means all modules will get 'jquery-private'
            // for their 'jquery' dependency.
            '*': { 'jquery': 'jquery-private' },

            // 'jquery-private' wants the real jQuery module
            // though. If this line was not here, there would
            // be an unresolvable cyclic dependency.
            'jquery-private': { 'jquery': 'jquery' }
        },
        shim: {
            jquery      : {
                exports : '$'
            },
            datatables  : {
                deps: [ "jquery" ]
            },
            underscore: {
                exports: '_'
            },
            backbone: {
                deps: ['underscore', 'jquery'],
                exports: 'Backbone'
            },
            oauth2      : {
                deps: ['underscore', 'jquery', 'backbone']
            },
            vextabdiv :  {
                deps    : [
                    'jquery'
                ]
            }
        }
    }
);

requirejs(
    [
        //- Security -//
        "/resources/client/module/security/Bootstrap.js",
        //- Song -//
        "/resources/client/module/user/Bootstrap.js"
    ],
    function(
        //- Security -//
        SecurityModule,
        //- Song -//
        UserModule
    ) {
    }
);
