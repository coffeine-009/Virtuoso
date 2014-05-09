/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */
var User = User || {};

User.SongView = Backbone.View.extend({
    /// *** Properties  *** ///
    //- Parrent DOM element -//
    el: "#main-content", 

    //- Template -//
    template: null, 


    /// *** Methods     *** ///
    events: {
        "click #test": "checkr"
    },

    initialize: function( Options ) {
        //- Init -//
        this.template = _.template( $( "#song" ).html() );
    },

    render: function() {
        $( this.el ).html( this.template() );

        return this;
    }, 

    checkr: function() {
        alert('ok');
    }
});