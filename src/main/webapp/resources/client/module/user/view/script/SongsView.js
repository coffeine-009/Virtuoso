/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */
var User = User || {};

define(
    [
        "tpl!/resources/client/module/user/view/templates/Songs.html"
    ],
    function(
        SongsTpl
    )
    {
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

            initialize: function (Options) {
                //- Init -//
                this.template = _.template( SongsTpl );
            },

            render: function () {
                $(this.el).html(this.template({songs: [
                    {id: 1, name: "Rose"},
                    {id: 2, name: " Red Rose"}
                ]}));
                $("#songst").dataTable();

                return this;
            },

            checkr: function () {
                alert('ok');
            }
        });
    }
);
