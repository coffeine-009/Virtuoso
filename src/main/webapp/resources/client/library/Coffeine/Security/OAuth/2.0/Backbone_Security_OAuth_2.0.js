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
        'oauth2'
    ],
    function($, _, Backbone, OAuth2) {

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

            /**
             * Token got from server
             *
             * @type {Token}
             */
            this.token = null;

            //- Get token if exists -//
            var token = JSON.parse(this.storage.getItem(this.STORRAGE_KEY));

            if ( token ) {
                this.token = new Coffeine.Security.OAuth._2.Token(
                    token.accessToken,
                    token.refreshToken,
                    token.expiresEnd,
                    token.scope
                );
            }

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
            },


            //- SECTION :: GET -//
            /**
             * Get token
             *
             * @returns {Token}
             */
            getToken: function()// : Token
            {
                return this.token;
            },


            //- SECTION :: SET -//
            /**
             * Tmp
             * //FIXME
             *
             * @param Controllers
             */
            setControllers: function(
                /*Array*/   Controllers,
                /*function*/CallBack
            )// : void
            {
                var self = this;

                if ( !(Controllers instanceof Array) ) {
                    // IllegalArgumentException
                    //TODO: impl
                    throw 'IllegalArgumentException';
                }

                for ( var i = 0; i < Controllers.length; i++ ) {
                    //- Set interceptor -//
                    Controllers[ i ].on(
                        'route',
                        function( Route, Params ) {
                            $.proxy(
                                self.checkAccess(
                                    Route,
                                    Params,
                                    CallBack
                                ),
                                self
                            );
                        }
                    );
                }
            },


            //- SECTION :: HELPER -//
            /**
             * Check access
             */
            checkAccess: function(
                /*string*/  Route,
                /*Array*/   Params,
                /*function*/CallBack
            )// : void
            {
                //- Return control -//
                if ( !this.token || this.token.isExpired() ) {
                    Backbone.history.navigate('#security/signin', {trigger: true});
                    return;
                }

                //- Success -//
                CallBack();
            },

            /**
             * Check access for Model
             *
             * @param Xhr
             */
            checkAccessModel: function(Xhr)// : void
            {
                //- Return control -//
                if ( !this.token || this.token.isExpired() ) {
                    Backbone.history.navigate('#security/signin', {trigger: true});
                    Xhr.abort();
                    return;
                }

                Xhr.setRequestHeader(
                    "Authorization",
                    "Bearer " + this.getToken().getAccessToken()
                );
            }
        });
    }
);
