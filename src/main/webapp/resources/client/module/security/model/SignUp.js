/*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
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
