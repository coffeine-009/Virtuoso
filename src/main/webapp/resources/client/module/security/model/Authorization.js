/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-27 19:48:15 - 2014-05-.. ..:..:..
 *
 * @address /Ukraine/Petranka
 */

var Security = Security || {};

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
        return Security.Authorization = Backbone.Model.extend({
            /// *** Properties  *** ///
            //- Path for get song from API -//
            urlRoot: "/j_spring_security_check",

            //- Set default values -//
            defaults: {
                "j_username"    : "",
                "j_password"    : ""
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
