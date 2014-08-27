/**
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-06-29 00:28:13 ::
 */
var User = User || {};

define(
    [
        "tpl!/resources/client/module/user/view/template/song/Create",
        "jquery",
        "underscore",
        "backbone"
    ],
    function(
        SongCreateTpl,
        $,
        _,
        Backbone
    ) {
        User.Song.CreateView = Backbone.View.extend({
            /// *** Properties  *** ///
            //- Parrent DOM element -//
            el: "#main-content",
            song: null,


            /// *** Methods     *** ///
            events: {
                "click #test": "checkr"
            },

            initialize: function () {
                //- Init -//
            },

            render: function () {
                $( this.el ).html( SongCreateTpl( {} ) );

                return this;
            },

            checkr: function () {
                alert('ok');
            }
        });
    }
);