/*
 * @copyright (c) 2015, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 1/8/15 6:44 PM :: ../../.. ..:.. ..
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

var User = User || {
    Controller: {}
};


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
        require(
            [
                //- Song -//
                "/resources/client/module/user/model/Song.js",
                "/resources/client/module/user/model/Songs.js",
                "/resources/client/module/user/view/script/song/CreateView.js",
                "/resources/client/module/user/view/script/song/SongView.js",
                "/resources/client/module/user/view/script/song/SongsView.js",
                "/resources/client/module/user/controller/SongController.js",
                "/resources/client/module/user/controller/ComposerController.js"
            ],
            function (
                //- Song -//
                SongModel,
                SongsModel,
                CreateView,
                SongView,
                SongsView,
                SongController,
                ComposerController
            ) {
                // Create a instance of song controller
                User.Controller.Song = new User.SongController();
                User.Controller.Composer = new ComposerController();
            }
        );
    }
);
