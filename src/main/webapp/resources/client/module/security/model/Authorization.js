/**
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-27 19:48:15 - 2014-05-27 23:00:00
 *
 * @address /Ukraine/Petranka
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
