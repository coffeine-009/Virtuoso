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
        /**
         * Model for work with song
         */
        return User.Song = Backbone.Model.extend({
            /// *** Properties  *** ///
            //- Path for get song from API -//
            urlRoot: "/user/song",

            //- Set default values -//
            defaults: {
                "id"    : -1
            },


            /// *** Methods     ***///
            /**
             * Initialization song.
             * Call after create instance of song
             */
            initialize: function () {

            }
        });
    }
);
