/*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
 */

var Security = Security || {};

define(
    [
        "backbone",
        "backboneoauth"
    ],
    function(
        Backbone,
        OAuth
    ) {
        /**
         * Model for work with song
         */
        return Security.Model.Authorization = Backbone.Model.extend({
            /// *** Properties  *** ///
            //- Path for get song from API -//
            urlRoot: "/oauth/token",

            //- Set default values -//
            defaults: {
                "username"      : "",
                "password"      : "",
                "client_id"     : "developer",
                "client_secret" : "developer32",
                "grant_type"    : "password",
                "scope"         : "read"
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
