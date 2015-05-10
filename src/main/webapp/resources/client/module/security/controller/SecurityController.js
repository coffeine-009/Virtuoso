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
                "security/signin"   : "signInAction",
                "security/signup"   : "signUpAction"
            },

            initialize: function() {
                //- Init -//

            },

            /**
             * Action SignIn. Display form
             */
            signInAction: function () {
                var view = new Security.View.SignInView();

                view.render();
            },

            /**
             * Action Sign Out
             */
            signOutAction: function() {
                //TODO: implement
            },

            /**
             * Action Registration. Display form
             */
            signUpAction: function() {
                var view = new Security.View.RegistrationView();

                view.render();
            }
        });
    }
);
