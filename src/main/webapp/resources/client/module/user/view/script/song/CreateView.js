/*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
 */

/**
 * User :: View :: Song :: Create
 * @version 1.0
 */
define(
    [
        "tpl!/resources/client/module/user/view/template/song/Create",
//        "jquery",
        "underscore",
        "backbone"
    ],
    function(
        SongCreateTpl,
//        $,
        _,
        Backbone
    ) {
        return Backbone.View.extend({
            /// *** Properties  *** ///
            //- Parrent DOM element -//
            el: "#main-content",
            song: null,
            composers: null,


            /// *** Methods     *** ///
            events: {
                "click #song-composer": "composerSelected"
            },

            initialize: function () {
                //- Init -//
            },

            render: function () {

                //- Append view to document -//
                $( this.el ).html(
                    SongCreateTpl(
                        {
                            composers: this.composers
                        }
                    )
                );

//                $('body').on('focus',"#song-writedate", function(){
//                    $(this).datepicker();
//                });
//                $('#song-writedate').datetimepicker();

                return this;
            },

            composerSelected: function () {
                console.log($("#song-composer").val());
            },

            //- SECTION :: SET -//
            /**
             * Set composers
             *
             * @param Composers
             */
            setComposers: function( Composers ) {
                this.composers = Composers;
            }
        });
    }
);
