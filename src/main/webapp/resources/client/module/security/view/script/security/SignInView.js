                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    /*
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

define(
    [
        "tpl!/resources/client/module/security/view/template/security/SignIn",
        "jquery",
        "underscore",
        "backbone",
        "backboneoauth"
    ],
    function(
        SignInTpl,
        $,
        _,
        Backbone,
        OAuth
        ) {
        Security.View.SignInView = Backbone.View.extend({
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
                this.model = new Security.Model.Authorization();
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
                Security.Model.OAuth2.access(
                    $( "#username").val(),
                    $( "#password").val(),
                    this.signinSuccess,
                    this.signinFailure
                );
//                this.model.create(
//                    {
//                        "username"  : $( "#username").val(),
//                        "password"  : $( "#password").val()
//                    },
//                    {
//                        success : this.signinSuccess,
//                        error   : this.signinFailure,
//                        beforeSend: function(xhr) {
//                            xhr.setRequestHeader("Authorization", "Basic ZGV2ZWxvcGVyOmRldmVsb3BlcjMy")
//                            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
//                        }
//                    }
//                );
            },

            /**
             * Success Sign In. Redirect to previous or home page
             *
             * @param Model
             * @param Response
             */
            signinSuccess: function( Response ) {
                //TODO: do redirect
                console.log( Response );
            },

            /**
             * Failure Sign In. Display errors
             *
             * @param Model
             * @param Response
             */
            signinFailure: function( Response ) {
                //TODO: display errors
                console.log( Error.status );
            }
        });
    }
);
