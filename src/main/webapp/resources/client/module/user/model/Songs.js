/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

var User = User || {};

define(
    [
        "backbone"
    ],
    function(
        Backbone
    ) {
        User.Songs = Backbone.Collection.extend({
            model: User.Song,
            url: "/user/song/list",

            initialize: function () {
//                this.fetch();
            }
        });
    }
);
