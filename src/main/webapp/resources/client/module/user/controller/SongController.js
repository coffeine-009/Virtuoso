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
            model   : null,
            views   : null,
            songList: null,

            routes: {
                "user/songs"    : "songlistAction",
                "user/song/:id" : "songAction"
            },

            initialize: function() {
                //- Init -//
                this.songList = new User.Songs();

                this.views = {
                    "songs" : new User.SongsView(),
                    "song"  : new User.SongView()
                };
            },

            /**
             * Action list of songs
             */
            songlistAction: function () {
                var self = this;
                this.songList.fetch(
                    {
                        success: function() {
                            self.views.songs.setSongs( self.songList.toJSON() );
                            self.views.songs.render();
                        },
                        error: function() {
                            alert("Error");//TODO: call message system
                        }
                    }
                );
            },

            songAction: function ( /*int*/Id ) {
                //- Create model -//
                var song = new User.Song(
                    {
                        id  : Id
                    }
                );
                //- Create view -//
                var view = new User.SongView();

                //TODO: Show msg wait

                //- Synchronization with server -//
                song.fetch(
                    {
                        success: function() {
                            song.setSong( song.toJSON() );
                        },
                        error: function() {
                            //TODO: call msg system
                        }
                    }
                );

                //- Render view -//
                view.render();

                //TODO: Hide msg wait
            }
        });
    }
);
