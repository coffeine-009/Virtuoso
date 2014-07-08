/*
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

define(
    [
        "backbone"
    ],
    function(
        Backbone
    ) {
        return Security.Controller.SecurityController = Backbone.Router.extend({
            /// *** Properties  *** ///

            routes: {
                "security/signin"       : "signinAction",
                "security/registration" : "registrationAction"
            },

            initialize: function() {
                //- Init -//

            },

            /**
             * Action SignIn. Display form
             */
            signinAction: function () {
                var view = new Security.SignInView();

                view.render();
            },

            /**
             * Action Registration. Display form
             */
            registrationAction: function() {
                var view = new Security.View.RegistrationView();

                view.render();
            }
        });
    }
);
