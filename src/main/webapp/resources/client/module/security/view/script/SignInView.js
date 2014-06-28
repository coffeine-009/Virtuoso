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

            /**
             * Binding events
             */
            events: {
                "click #security-signin": "submit"
            },


            /// *** Methods     *** ///
            /**
             * Initialize Sign In form
             */
            initialize: function () {
                //- Init -//
                this.model = new Security.Authorization();
            },

            /**
             * Render form for Sign In
             *
             * @returns {Security.SignInView}
             */
            render: function () {
                $( this.el ).html( SignInTpl( {} ) );

                return this;
            },


            /**
             * Submit Sign In form
             */
            submit: function() {
                //- Send request for Sign In -//
                this.model.save(
                    {
                        "username"  : $( "#username").val(),
                        "password"  : $( "#password").val()
                    },
                    {
                        success : this.signinSuccess,
                        error   : this.signinFailure
                    }
                );
            },

            /**
             * Success Sign In. Redirect to previous or home page
             *
             * @param Model
             * @param Response
             */
            signinSuccess: function( Model, Response ) {
                //TODO: do redirect
                console.log( Response );
            },

            /**
             * Failure Sign In. Display errors
             *
             * @param Model
             * @param Response
             */
            signinFailure: function( Model, Response ) {
                //TODO: display errors
                console.log( Error.status );
            }
        });
    }
);
