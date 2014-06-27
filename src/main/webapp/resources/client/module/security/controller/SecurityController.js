/*
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

var Security = Security || {};

define(
    [
        "backbone"
    ],
    function(
        Backbone
        ) {
        Security.SecurityController = Backbone.Router.extend({
            /// *** Properties  *** ///

            routes: {
                "security/signin"   : "signinAction"
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
            }
        });
    }
);
