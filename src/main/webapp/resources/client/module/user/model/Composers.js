/*
 * @copyright (c) 2015, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 3/22/15 1:12 PM :: ../../.. ..:.. ..
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

/**
 * User :: Model :: Composers
 *
 * @version 1.0
 */
define(
    [
        "backbone",
        "/resources/client/module/user/model/Composer.js"
    ],
    function(
        Backbone,
        Composer
    ) {
        return Backbone.Collection.extend({
            model: Composer,
            url: "composers",

            initialize: function () {
//                this.fetch();
            }
        });
    }
);
