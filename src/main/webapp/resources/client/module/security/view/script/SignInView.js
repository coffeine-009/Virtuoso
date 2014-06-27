/* 
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */
var Security = Security || {};

define(
    [
        "tpl!/resources/client/module/security/view/template/SignIn",
        "jquery",
        "underscore",
        "backbone"
    ],
    function(
        SignInTpl,
        $,
        _,
        Backbone
        ) {
        Security.SignInView = Backbone.View.extend({
            /// *** Properties  *** ///
            //- Parrent DOM element -//
            el: "#main-content",

            model: null,


            /// *** Methods     *** ///
            events: {
                "click #security-signin": "submit"
            },

            initialize: function () {
                //- Init -//
                this.model = new Security.Authorization();
            },

            render: function () {
                $( this.el ).html( SignInTpl( {} ) );

                return this;
            },


            submit: function() {
                this.model.set( "j_username", $( "#username").val() );
                this.model.set( "j_password", $( "#password").val() );

                this.model.save();
            }
        });
    }
);