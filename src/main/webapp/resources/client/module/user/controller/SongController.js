/**
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
 */

var User = User || {};

define(
    [
        "backbone",
        "/resources/client/module/user/view/script/song/CreateView.js",
        //- Model -//
        "/resources/client/module/user/model/Composers.js"
    ],
    function(
        Backbone,
        CreateView,
        //- Model -//
        Composers
    ) {
        return User.SongController = Backbone.Router.extend({

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
                        beforeSend: function( Xhr ) {
                            $.proxy(
                                Security.Model.OAuth2.checkAccessModel(Xhr),
                                Security.Model.OAuth2
                            )
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
                //- Create model -//
                var composers = new Composers();

                //- Create view  -//
                var view = new CreateView();

                //- Synchronization -//
                var composersLoaded = false;
                var poetsLoaded = true;
                var synchronize = function() {
                    if (composersLoaded && poetsLoaded) {
                        //- Render view -//
                        view.render();
                    }
                };

                //- Fetch composer's data -//
                composers.fetch({
                    beforeSend: function( Xhr ) {
                        //$.proxy(
                        //    Security.Model.OAuth2.checkAccessModel(Xhr),
                        //    Security.Model.OAuth2
                        //)
                    },
                    success: function() {
                        //- Mark that composers are loaded -//
                        composersLoaded = true;

                        //- Set data about composers -//
                        view.setComposers( composers.toJSON() );

                        synchronize();
                    },
                    error: function() {
                        alert("Error");//TODO: call message system
                    }
                });
            }
        });
    }
);
