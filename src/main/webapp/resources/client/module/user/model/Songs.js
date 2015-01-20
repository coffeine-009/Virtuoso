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
        return User.Songs = Backbone.Collection.extend({
            model: User.Song,
            url: "/user/song/list/1/10",

            initialize: function () {
//                this.fetch();
            }
        });
    }
);
