/**
 * @copyright (c) 2015 by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

/**
 *
 */
var User = User || {};

define(
    [
        "backbone",
        "/resources/client/module/user/view/script/composer/CreateView.js"
    ],
    function(
        Backbone,
        CreateView
    ) {
        var ComposerController = Backbone.Router.extend({

            /// *** Properties  *** ///

            routes: {
                "user/composer/create": "composerCreateAction"
            },

            initialize: function () {
                //- Init -//
            },

            /**
             * Action: create a new composer
             */
            composerCreateAction: function () {
                var view = new CreateView();
                view.render();
            }
        });

        return ComposerController;
    }
);