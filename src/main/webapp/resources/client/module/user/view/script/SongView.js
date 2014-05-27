/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */
var User = User || {};

define(
    [
        "tpl!/resources/client/module/user/view/templates/Song",
        "jquery",
        "underscore",
        "backbone"
    ],
    function(
        SongTpl,
        $,
        _,
        Backbone
        ) {
        User.SongView = Backbone.View.extend({
            /// *** Properties  *** ///
            //- Parrent DOM element -//
            el: "#main-content",
            song: null,

            //- Template -//
            template: null,


            /// *** Methods     *** ///
            events: {
                "click #test": "checkr"
            },

            initialize: function () {
                //- Init -//
//                this.template = _.template( SongTpl( {} ) );
            },

            render: function () {
                $( this.el ).html( SongTpl( this.song ) );

                return this;
            },

            checkr: function () {
                alert('ok');
            },

            setSong: function( Song ) {
                this.song = Song;
            }
        });
    }
);