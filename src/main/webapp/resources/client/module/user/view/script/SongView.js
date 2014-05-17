/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */
var User = User || {};

define(
    [
        "jquery",
        "underscore",
        "backbone"
    ],
    function(
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

            initialize: function (Options) {
                //- Init -//
                this.template = _.template($("#song").html());
            },

            render: function () {
                $(this.el).html(this.template());

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