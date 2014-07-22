/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-07-17 22:08:40 - 2014-05-27 23:00:00
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

define(
    [
        "backbone"
    ],
    function(
        Backbone
        ) {
        /**
         * Model for work with Registration a new users
         */
        return Security.Model.SignUp = Backbone.Model.extend({
            /// *** Properties  *** ///
            //- Path for get song from API -//
            urlRoot: "/security/signup",

            //- Set default values -//
            defaults: {
                "locale"    : ""
            },


            /// *** Methods     ***///
            /**
             * Initialization authorization.
             */
            initialize: function () {

            }
        });
    }
);
