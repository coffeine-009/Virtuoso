/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-05-09 22:32:08 - 2014-05-.. ..:..:..
 *
 * @address /Ukraine/Ivano-Frankivsk
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
