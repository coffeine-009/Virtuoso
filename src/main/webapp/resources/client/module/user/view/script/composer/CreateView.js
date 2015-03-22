/**
 * @copyright (c) 2015 by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

/**
 * Composer :: Create :: View
 *
 * @version 1.0
 */
define(
    [
        "tpl!/resources/client/module/main/view/template/helper/window/Modal",
        "tpl!/resources/client/module/user/view/template/composer/Create"
    ],
    function(
        WindowModalTpl,
        CreateTpl
    ) {
        return Backbone.View.extend({

            /// *** Properties  *** ///
            el: "#message-container",

            events: {
                "click #security-signin": "doSubmit"
            },

            /**
             * Render form for create a new Composer
             *
             * @returns CreateView
             */
            render: function() {
                $( this.el).html(
                    WindowModalTpl({
                        body: CreateTpl({})
                    })
                );

                $('#myModal_').modal();

                return this;
            },

            /**
             * Submit form for create a new Composer
             */
            doSubmit: function() {
                alert('OK');
            }
        });
    }
);