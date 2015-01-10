/*
 * @copyright (c) 2015, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 1/10/15 1:25 PM :: ../../.. ..:.. ..
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

/**
 * Module: Backbone OAuth 2.0
 *
 * @version 1.0
 */
define(
    [
        'jquery',
        'underscore',
        'backbone',
    ],
    function($, _, Backbone) {

        /**
         * OAuth 2.0
         * Backbone module
         *
         * @constructor
         */
        Backbone.OAuth2 = function(SecurityProvider) {

            /// *** Constants   *** ///
            /**
             * Tge key used for local storage
             *
             * @type {string}
             */
            this.STORRAGE_KEY = '__oauth2';


            /// *** Properties  *** ///
            /**
             * Token got from server
             *
             * @type {Token}
             */
            this.token = null;

            /**
             * OAuth 2.0 provider
             *
             * @type {Security_OAuth_2.0}
             */
            this.provider = SecurityProvider;

            /**
             * Storage
             *
             * @type {Storage}
             */
            this.storage = window.localStorage;

            /*
             * Invoke initialize method
             */
            this.initialize.apply(this, arguments);
        };

        /// **  Methods     *** //
        //- Extends Backbone.Events -//
        _.extend(Backbone.OAuth2.prototype, Backbone.Events, {

            /**
             * Initialization
             */
            initialize: function() {

            },

            /**
             * Authenticate
             *
             * @param Username
             * @param Password
             * @param CallBackSuccess
             * @param CallBackFailure
             */
            authenticate: function(
                /*string*/  Username,
                /*string*/  Password,
                /*function*/CallBackSuccess,
                /*function*/CallBackFailure
            )// : void
            {
                //- Store reference to object -//
                var self = this;

                //- Authenticate user -//
                this.provider.Authenticate(
                    Username,
                    Password,
                    // Success
                    function(Token) {
                        //- Save token -//
                        self.storage.setItem(
                            self.STORRAGE_KEY,
                            JSON.stringify(
                                self.token = Token
                            )
                        );

                        CallBackSuccess();
                    },
                    CallBackFailure
                );
            }
        });
    }
);
