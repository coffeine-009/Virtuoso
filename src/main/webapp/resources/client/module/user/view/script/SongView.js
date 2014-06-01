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
        "backbone",
        "vextab",
        "vextabdiv"
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

//                Vex.Flow.Artist.DEBUG = true;
//                Vex.Flow.VexTab.DEBUG = true;

                renderer = new Vex.Flow.Renderer($('#editor-notes')[0],
                    Vex.Flow.Renderer.Backends.CANVAS);

                artist = new Vex.Flow.Artist(10, 10, 600, {scale: 0.8});
                vextab = new Vex.Flow.VexTab(artist);

                function render() {
                    try {
                        vextab.reset();
                        artist.reset();
                        vextab.parse($("#blah").val());
                        artist.render(renderer);
                        $("#error").text("");
                    } catch (e) {
                        console.log(e);
                        $("#error").html(e.message.replace(/[\n]/g, '<br/>'));
                    }
                }

                $("#blah").keyup(_.throttle(render, 250));
                render();

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