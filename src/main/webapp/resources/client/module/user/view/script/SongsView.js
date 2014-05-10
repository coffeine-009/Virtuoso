/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */
var User = User || {};

User.SongsView = Backbone.View.extend({
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
        this.template = _.template( "<table id='songst'>"
                +"<thead>"
                    +"<tr>"
                        +"<th>#</th>"
                        +"<th>Name</th>"
                    +"</tr>"
                +"</thead>"
                +"<tbody>"
                    +"<% _.each( songs, function( song ){ %>"
                    +"<tr>"
                        +"<td><%- song.id  %></td>"
                        +"<td><%- song.name  %></td>"
                    +"</tr>"
                    +"<% }); %>"
                +"</tbody>"
            +"</table>" );
    },

    render: function() {
        $( this.el ).html( this.template({songs:[{id:1,name:"Rose"},{id:2,name:" Red Rose"}]}) );
	$("#songst").dataTable();

        return this;
    }, 

    checkr: function() {
        alert('ok');
    }
});
