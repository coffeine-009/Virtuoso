/*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
 */

define(
    [
        "tpl!/resources/client/module/security/view/template/security/SignUp",
        "jquery",
        "underscore",
        "backbone"
    ],
    function(
        RegistrationTpl,
        $,
        _,
        Backbone
    ) {
        return Security.View.RegistrationView = Backbone.View.extend({

            /// *** Properties  *** ///
            //- Parrent DOM element -//
            el: "#main-content",

            //- Fields -//
            field: {
                username        : "#username",
                password        : "#password",
                password_confirm: "#password_confirm",
                firstName       : "#first_name",
                lastName        : "#last_name",
                gender          : "#gender",
                role            : "#role",
                language        : "#language",
                country         : "#country"
            },

            model: null,

            //- Events -//
            events: {
                "click #signup-submit"  : "submit"
            },


            /// *** Methods     *** ///
            /**
             * Initialization Registration view
             */
            initialize: function () {
                //- Init -//
                this.model = new Security.Model.SignUp();
            },

            /**
             * Render form
             *
             * @returns {Security.View.RegistrationView}
             */
            render: function () {
                $( this.el ).html( RegistrationTpl( {} ) );

                return this;
            },

            /**
             * Submit form for registration
             */
            submit: function() {
                //- Init model -//
                this.model.set(
                    {
                        username    : $( this.field.username ).val(),
                        password    : $( this.field.password ).val(),
                        firstName   : $( this.field.firstName ).val(),
                        lastName    : $( this.field.lastName ).val(),
                        gender      : $( this.field.gender ).val(),
                        role        : $( this.field.role ).val(),
                        locale      : $( this.field.language ).val()
                                        + '-'
                                        + $( this.field.country ).val()
                    }
                );

                this.model.save(
                    {},
                    {
                        success: function( Model, Response ) {
//                            console.log(Response,b);
                        },
                        error: function( Model, Response ) {
//                            console.log(a,b);
                        }
                    }
                );
            }
        });
    }
);
