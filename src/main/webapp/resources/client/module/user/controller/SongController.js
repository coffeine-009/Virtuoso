    /*
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

var User = User || {};

define(
    [
        "backbone"
    ],
    function(
        Backbone
        ) {
        User.SongController = Backbone.Router.extend({
            /// *** Properties  *** ///
            model: null,
            views: null,
            songList: null,

            routes: {
                "user/songs": "songlistAction",
                "user/song/": "songAction"
            },

            initialize: function (Options) {
                //- Init -//
                //        this.model = new User.Song({id:1});

                if (this.views === null) {
                    this.views = [
                        new User.SongsView(),
                        new User.SongView()
                    ];
                }
            },

            /**
             * Action list of songs
             */
            songlistAction: function () {
                if (!this.songList) {
                    this.songList = new User.Songs();
                }
                this.views[ 0 ].setSongs( this.songList.getSongs() );
                this.views[ 0 ].render();
            },

            songAction: function () {
                this.views[ 1 ].render();
            }
        });
    }
);
