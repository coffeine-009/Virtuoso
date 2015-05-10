/**
 * @copyright (c) 2015 by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

/**
 * User :: Controller :: Composer
 * Controller(Router) for composer
 *
 * @version 1.0
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
                //- Create view -//
                var view = new CreateView();

                //- Render view -//
                view.render();
            }
        });

        return ComposerController;
    }
);