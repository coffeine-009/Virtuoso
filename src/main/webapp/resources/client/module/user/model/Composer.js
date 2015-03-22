/*
 * @copyright (c) 2015, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 3/22/15 1:10 PM :: ../../.. ..:.. ..
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

/**
 * User :: Model :: Composer
 *
 * @version 1.0
 */
define(
    [
        "backbone"
    ],
    function(
        Backbone
    ) {
        /**
         * Model for work with composer
         */
        return Backbone.Model.extend({
            /// *** Properties  *** ///
            //- Path for get composer from REST Service -//
            urlRoot: "composers",

            //- Set default values -//
            defaults: {
                "id"    : -1
            },


            /// *** Methods     ***///
            /**
             * Initialization composer.
             * Call after create instance of composer
             */
            initialize: function () {

            }
        });
    }
);
