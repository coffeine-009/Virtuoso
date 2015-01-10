/*
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

define(
    [
        "tpl!/resources/client/module/main/view/template/helper/window/Modal",
        "tpl!/resources/client/module/security/view/template/security/SignIn",
        "tpl!/resources/client/module/security/view/template/security/AuthenticateSuccess",
        "tpl!/resources/client/module/security/view/template/security/AuthenticateFailure",
        "underscore",
        "backbone",
        "backboneoauth"
    ],
    function(
        Modal,
        SignInTpl,
        AuthenticateSuccessTpl,
        AuthenticateFailureTpl,
        _,
        Backbone,
        OAuth
    ) {
        Security.View.SignInView = Backbone.View.extend({
            /// *** Properties  *** ///
            //- Parent DOM element -//
            el: "#message-container",

            /**
             * Container for account manager UI
             *
             * @type string
             */
            authenticateElement: "#security",

            /**
             * Container for display errors
             */
            errorsContainer: '#security-authorization-errors',

            model: null,

            /**
             * Binding events
             */
            events: {
                "click #security-signin": "render",
                "click #security-signin-submit": "submit",
                "click #security-signin-cancel": "cancel",
                "click #security-signin-close": "cancel"
            },


            /// *** Methods     *** ///
            /**
             * Initialize Sign In form
             */
            initialize: function () {
                //- Init -//
                this.model = new Security.Model.Authorization();
            },

            /**
             * Render form for Sign In
             *
             * @returns {Security.SignInView}
             */
            render: function () {
                $( this.el ).html( SignInTpl( {} ) );

                $( this.errorsContainer ).hide();

                $('#myModal').modal();

                return this;
            },


            /**
             * Submit Sign In form
             */
            submit: function() {
                //- Send request for Sign In -//
                Security.Model.OAuth2.authenticate(
                    $( "#username").val(),
                    $( "#password").val(),
                    $.proxy( this.signinSuccess, this ),
                    $.proxy( this.signinFailure, this )
                );
            },

            /**
             * Success Sign In. Redirect to previous or home page
             *
             * @param Model
             * @param Response
             */
            signinSuccess: function() {
                //- Success -//
                //- Hide Sign In view -//
                $('#myModal').modal('hide')

                // ReRender account manager UI container
                $(this.authenticateElement).html(
                    AuthenticateSuccessTpl({})
                );
            },

            /**
             * Failure Sign In. Display errors
             *
             * @param Model
             * @param Response
             */
            signinFailure: function( Response ) {
                //- Failure -//
                //- Show errors -//
                $( this.errorsContainer ).html('Invalid username or password');
                $( this.errorsContainer ).show();

                // ReRender account manager UI container
                $(this.authenticateElement).html(
                    AuthenticateFailureTpl({})
                );

                //TODO: display errors
                console.log( Response );
            },

            /**
             * Cancel sign in.
             * Redirect back to main page
             */
            cancel: function() {
                // Redirect to main page
                window.location.hash = '#';
            }
        });
    }
);
