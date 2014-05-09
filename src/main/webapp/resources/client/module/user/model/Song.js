/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

var User = User || {};

User.Song = Backbone.Model.extend({

    urlRoot : "/Virtuoso/user/song", 

    defaults: {
        "id"    : "0"
    }, 
    
    initialize: function() {
//        this.fetch({
//            success : this.success, 
//            error   : this.error
//        });
    },

    success: function( Collection, Response ) {
        console.log(Response);
        console.log(this);
    }, 

    error: function( Collection, Response ) {
        console.log(Response);
    }
});
