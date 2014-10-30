/*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
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
