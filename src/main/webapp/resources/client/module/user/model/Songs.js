/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

var User = User || {};

User.Songs = Backbone.Collection.extend({
    model   : User.Song, 
    url     : "/Virtuoso/user/song/list", 

    initialize: function() {
        this.fetch({
            success : this.success, 
            error   : this.error
        });
    },

    success: function( Collection, Response ) {
        console.log(Collection.models.length);
    }, 

    error: function( Collection, Response ) {
        console.log(Response);
    }
});
