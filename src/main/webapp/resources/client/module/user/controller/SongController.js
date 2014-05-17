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

            routes: {
                "user/songs"    : "songlistAction",
                "user/song/:id" : "songAction"
            },

            initialize: function() {
                //- Init -//

            },

            /**
             * Action list of songs
             */
            songlistAction: function () {
                var songList = new User.Songs();
                var view = new User.SongsView();

                songList.fetch(
                    {
                        success: function() {
                            view.setSongs( songList.toJSON() );
                            view.render();
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
                            view.setSong( song.toJSON() );
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
