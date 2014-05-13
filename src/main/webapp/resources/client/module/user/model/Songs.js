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
                this.fetch({
                    success: this.success,
                    error: this.error
                });
            },

            success: function ( Collection, Response ) {
                console.log(Collection.models[0].toJSON());
            },

            error: function (Collection, Response) {
                console.log(Response);
            },
            getSongs: function() {
                return this.models;
            }
        });
    }
);
