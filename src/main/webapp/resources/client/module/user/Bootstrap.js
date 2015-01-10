/*
 * @copyright (c) 2015, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 1/8/15 6:44 PM :: ../../.. ..:.. ..
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

define(
    [
        "jquery",
        "underscore",
        "backbone",
        "backboneoauth"
    ],
    function(
        $,
        _,
        Backbone,
        OAuth
    ) {
        // Include dependencies of module
        requirejs(
            [
                //- Song -//
                "/resources/client/module/user/model/Song.js",
                "/resources/client/module/user/model/Songs.js",
                "/resources/client/module/user/view/script/song/CreateView.js",
                "/resources/client/module/user/view/script/song/SongView.js",
                "/resources/client/module/user/view/script/song/SongsView.js",
                "/resources/client/module/user/controller/SongController.js"
            ],
            function (
                  //- Song -//
                  SongModel,
                  SongsModel,
                  SongView,
                  SongsView,
                  SongController
            ) {
                new User.SongController();
            }
        );
    }
);
