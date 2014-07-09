/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */
var User = User || {};

define(
    [
        "tpl!/resources/client/module/user/view/template/song/Songs",
        "underscore",
        "backbone",
        "jquery"
    ],
    function(
        SongsTpl,
        _,
        Backbone
    ) {
        User.SongsView = Backbone.View.extend({
            /// *** Properties  *** ///
            //- Parrent DOM element -//
            el: "#main-content",

            //- Template -//
            template: null,
            songs: [],


            /// *** Methods     *** ///
            events: {
                "click #test": "checkr"
            },

            setSongs: function( Models ) {
                this.songs = Models;
            },

            initialize: function () {
                //- Init -//
                //this.template = _.template( SongsTpl );
            },

            render: function () {
                $(this.el).html( SongsTpl( { songs: this.songs } ) );
//                $("#songst").dataTable(
////                    {
////                        ajax    : "/user/song/list"
////                    }
//                );

                return this;
            },

            checkr: function () {
                alert('ok');
            }
        });
    }
);
