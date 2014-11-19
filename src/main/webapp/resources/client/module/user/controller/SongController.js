    /*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
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
                "user/songs"        : "songlistAction",
                "user/song/create"  : "songCreateAction",
                "user/song/:id"     : "songAction"
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
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader("Authorization", Security.Model.OAuth2.getAuthorizationHeader().authorization)
                        },
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

                            //- Render view -//
                            view.render();
                        },
                        error: function() {
                            //TODO: call msg system
                        }
                    }
                );

                //TODO: Hide msg wait
            },

            /**
             * Create new song
             */
            songCreateAction: function() {
                var view = new User.Song.CreateView();

                view.render();
            }
        });
    }
);
