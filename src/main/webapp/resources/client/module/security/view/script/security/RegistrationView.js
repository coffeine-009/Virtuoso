/**
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-07-07 20:58:56 ::
 */
var Security = Security || {};

define(
    [
        "tpl!/resources/client/module/security/view/template/security/SignUp",
        "jquery",
        "underscore",
        "backbone"
    ],
    function(
        RegistrationTpl,
        $,
        _,
        Backbone
    ) {
        return Security.View.RegistrationView = Backbone.View.extend({
            /// *** Properties  *** ///
            //- Parrent DOM element -//
            el: "#main-content",



            /// *** Methods     *** ///
            events: {

            },

            initialize: function () {
                //- Init -//
            },

            render: function () {
                $( this.el ).html( RegistrationTpl( {} ) );

                return this;
            }
        });
    }
);