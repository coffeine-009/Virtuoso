/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-05-06 22:00:00 :: 2014-05-07 23:32:08
 *
 * @address /Ukraine/Ivano-Frankivsk/Chornovola/114/51
 */
var User = User || {};

requirejs.config(
    {
        baseUrl : "/resources/client/library",
        paths   : {
            templates   : "/resources/client/module/user/view/templates",
            tpl         : "/resources/client/library/tpl",
            text        : "/resources/client/library/text",
//            jquery      : "/resources/client/library/jquery",
            datatables  : "/resources/client/library/jquery.dataTables",
            underscore  : "/resources/client/library/underscore",
            backbone    : "/resources/client/library/backbone",
            vextab      : "/resources/client/library/vexflow-min",
            vextabdiv   : "/resources/client/library/tabdiv-min",
            user        : "/resources/client/module/user"
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
        "/resources/client/module/user/model/Song.js",
        "/resources/client/module/user/model/Songs.js",
        "/resources/client/module/user/view/script/SongView.js",
        "/resources/client/module/user/view/script/SongsView.js",
        "/resources/client/module/user/controller/SongController.js"
    ],
    function(
        SongModel,
        SongsModel,
        SongView,
        SongsView,
        SongController
    ) {
        new User.SongController();

        Backbone.history.start();
    }
);
