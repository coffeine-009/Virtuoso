/*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
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
                "security/signin"   : "signinAction",
                "security/signup"   : "signupAction"
            },

            initialize: function() {
                //- Init -//

            },

            /**
             * Action SignIn. Display form
             */
            signinAction: function () {
                var view = new Security.View.SignInView();

                view.render();
            },

            /**
             * Action Sign Out
             */
            signoutAction: function() {
                //TODO: implement
            },

            /**
             * Action Registration. Display form
             */
            signupAction: function() {
                var view = new Security.View.RegistrationView();

                view.render();
            }
        });
    }
);
